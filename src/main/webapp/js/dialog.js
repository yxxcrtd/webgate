//----------------------------------------------------------------------------------------------------
// [作    者] walkerwang
// [邮    箱] walkerwzy@gmail.com
// [作者博客] http://walkerwang.cnblogs.com
// [更新日期] 2013-4-26
// [版 本 号] v 0.1.3
// [使用方法]
// var d=new dialog(options).show();
//=====================================================================================
var dialog = function (conf) {
    var self = this,
        defaults = {
            namespace: "dragDialog",//命名空间、ID前缀
            modal: false,//显示成modal窗体（即有遮罩层）
            dragable: true,//可拖拽
            resizable: false,//可更改大小
            html: '',//加载html字符串
            ajax: '',//加载同域页面
            iframe: '',//iframe方式加载本域或跨域页面
            title: 'Title',//弹窗标题
            width: 600,//弹窗宽度
            height: 320,//弹窗高度
            outer: false,//允许弹窗被拖到窗体可见区域外
            dragMargin: { top: 0, bottom: 0, left:0, right:0 },//如果outer=false，设定弹窗与窗体四周的边距
            maximum: true,//允许最大化
            loading: '',//加载页面时的提示//可以传入文字，也可以外部用css来定义.dlg-loading的样式
            bootstrap: false, //是否用bootstrap样式
            maximize : false
        };

    //apply options
    self.options = $.extend(true, {}, defaults, conf);

    //variable
    var str_dlg = '<div class="dlg-container dlg-zindex">' +
        '<div class="dlg-header">' +
            '<div class="dlg-op"><span class="dlg-max" style="display:none;"></span><span class="dlg-restore" style="display:none;"></span><span class="dlg-close"></span></div>' +
            '<div class="dlg-icon"></div>' +
            '<div class="dlg-title"></div>' +
        '</div>' +
        '<div class="dlg-body"></div>' +
        '<div class="dlg-footer"><div class="dlg-resize"></div></div>' +
        '</div>',
    str_dlg_bootstrap='<div class="dlg-container dlg-zindex modal" style="width:auto;">'+
        '<div class="dlg-header modal-header">'+
          '<div class="dlg-op"><span class="dlg-max" style="display:none;"></span><span class="dlg-restore" style="display:none;"></span>'+
            '<button type="button" class="close dlg-close" data-dismiss="modal" aria-hidden="true">&times;</button>'+
          '</div>'+
        '<h3 class="dlg-title">Modal header</h3>'+
        '</div>'+
        '<div class="modal-body dlg-body"></div>'+
        '<div class="modal-footer dlg-footer" unselectable="on" style="-moz-user-select:none;" onselectstart="return false;"><div class="dlg-resize"></div></div>'+
        '</div>',
    str_cover = '<div id="commonDragModalCover" class="dlg-cover dlg-zindex"></div>',
    zindex_start = 100000,
    maxed = false,
    resizeObj = self.options.namespace + "_onresize",
    css = {
        cover: { height: '100%', width: '100%', opacity: 0.5, backgroundColor: '#fff', position: 'fixed', top: 0, left: 0, display: 'none', zIndex: 99999 },
        container: { position: 'absolute', top: '50%', left: '50%' },
        header: { overflow: 'hidden'},
        option: { float: 'right' },
        title: { float: 'left' },
        icon: { float: 'left' },
        close: { cursor: 'pointer' },
        body: { backgroundColor: '#fff', overflow: 'auto' },
        resize: { position: 'absolute', right: 0, bottom: 0, width: 15, height: 15, cursor: 'nw-resize' },
        loading: {}
    },
    getActiveDialog = function () {
        return $($('.dlg-container').get().sort(function (a, b) { return parseInt($(b).css('z-index'), 10) - parseInt($(a).css('z-index'), 10); })[0]);
    },
    getMaxZindex = function () {
        var exists = $('.dlg-zindex');
        if (!exists) return zindex_start;
        var ids = exists.map(function () { return parseInt($(this).css('z-index'), 10); }).get();
        return Math.max.apply(Math, ids);
    },
    getStackedMargin = function () {
        var exists = $('.dlg-container');
        return 24 * exists.length;
    },
    getDialogBody = function () {
        return self.dialog.find('.dlg-body');
    },
    setDialogCentral = function () {
        self.dialog.css({ top: '50%', left: '50%', marginLeft: function () { return 0 - parseInt($(this).width() / 2, 10) + getStackedMargin(); }, marginTop: function () { return 0 - parseInt($(this).height() / 2, 10) + getStackedMargin() - 80; } });
    },
    setWeightHeight = function () {
        var body = getDialogBody();
        //iframe
        body.find('iframe').css({ width: body.width(), height: body.height() });
        //ie6,7 bugfix,
        if ($.browser.msie && parseInt($.browser.version, 10) < 8)
            self.dialog.css({ width: body.width() });
    },
    showLoading = function () {
        getDialogBody().html('<span class="dlg-loading">' + self.options.loading + '</span>');
    },
    hideLoading = function () {
        getDialogBody().find('.dlg-loading').remove();
    };

    if(self.options.bootstrap) str_dlg=str_dlg_bootstrap;

    //create cover
    self.cover = null;
    if (self.options.modal) {
        if (!self.cover) {
            self.cover = $(str_cover).hide().css(css.cover).css({ zIndex: getMaxZindex() + 1 }).appendTo($('body'));
        }
    };

    //create dialog
    self.dialog = $(str_dlg).hide().appendTo($('#commonDragModal'));
    self.id = self.options.namespace + $('.dlg-container').length;
   // self.id = self.options.namespace;
    self.dialog.attr('id', self.id)
        .css({ zIndex: function () { return getMaxZindex() + 1; } })
        .find('.dlg-title').text(self.options.title);
    self.dialog = $("#" + self.id);

    //apply style
    self.dialog.css(css.container)
        .find('.dlg-header').css(css.header)
        .find('.dlg-op').css(css.option)
        .find('.dlg-close').css(css.close).end().end()
        .end().find('.dlg-title').css(css.title)
        .end().find('.dlg-icon').css(css.icon)
        .end().find('.dlg-body').css(css.body).css({ width: self.options.width, height: self.options.height })
        .end().find('.dlg-resize').css(css.resize)
        .end().find('.dlg-loading').css(css.loading);
    if(self.options.bootstrap) self.dialog.find('.modal-body').css('max-height','none');

    //attatch event
    self.dialog.on('mousedown', function () {
        var o = $(this),
            thisindexid = parseInt(o.css('z-index'), 10) || zindex_start,
            maxid = getMaxZindex();
        if (o.is('.dlg-close')) return;
        if (thisindexid == maxid) return;
        $('.dlg-active').removeClass('dlg-active');
        o.addClass('dlg-active').css('zIndex', maxid + 1);
    })
        .find('.dlg-close').on('click', function () { self.close(); });
    if (self.options.maximum) {
        self.dialog.find('.dlg-header').on('dblclick', function () {
            if (maxed) self.restoreWindow();
            else self.maxWindow();
        })
        .find('.dlg-max').on('click', function () {
            self.maxWindow();
        })
        .css('display', 'inline-block')
        .next('.dlg-restore').on('click', function () {
            self.restoreWindow();
        })
    };
    $(window).resize(function () {
        if (maxed) { maxed = false; self.maxWindow(true); return; }
    });

    //dragable
    if (self.options.dragable) self.dialog.dragable({ handler: '.dlg-header', offset: self.options.dragMargin, outer: self.options.outer });

    //resizable
    if (self.options.resizable) {
        self.dialog.find('.dlg-resize')
        .off('mousedown').on('mousedown', function (e) {
            var body = getDialogBody();
            $("." + resizeObj).removeClass(resizeObj);
            $(this).data({
                startx: e.pageX,
                starty: e.pageY,
                width: body.width(),
                height: body.height()
            }).addClass(resizeObj);
        });
        $('*').off('mousemove').off('mouseup')
        .on('mousemove', function (e) {
            var o = $('.' + resizeObj);
            if (o.length == 0) return;
            var x = e.pageX - parseInt(o.data('startx'), 10),
            y = e.pageY - parseInt(o.data('starty'), 10),
            body = o.parents('.dlg-container').eq(0).find('.dlg-body').eq(0);
            body.css({
                width: function () { return parseInt(o.data('width'), 10) + x; },
                height: function () { return parseInt(o.data('height'), 10) + y; }
            });
            setWeightHeight();
        })
        .on('mouseup', function () {
            $('.' + resizeObj).removeClass(resizeObj);
        });
    } else {
        self.dialog.find('.dlg-resize').css({ cursor: 'default', backgroundImage: 'none' });
    };

    //methods
    //load contents and show dialog
    self.show = function () {
        var body = getDialogBody();
        if (self.options.html) {
            body.html(self.options.html);
        } else if (self.options.ajax) {
            showLoading();
            body.load(self.options.ajax, function () { hideLoading(); });
        } else if (self.options.iframe) {
            showLoading();
            var iframe = $('<iframe/>', { width: body.width(), height: body.height(), src: self.options.iframe }).css({ border: 'none', opacity: 0 })
            .appendTo(body)
            .load(function () { hideLoading(); iframe.animate({ opacity: 1 }) });
        } else body.html('content here');

        //position          
        setDialogCentral();

        //for ie6,7
        setWeightHeight();
        if (self.options.maximize === true) {
        	self.maxWindow();
        }
        
        self.dialog.find(".modal-footer.dlg-footer").each(function(i, o) {
        	$(o).attr("title", "双击关闭");
        	$(o).css({
        		"cursor" : "pointer"
        	});
        	$(o).dblclick(function() {
        		self.close();
        	});
        });

        //set active
        $(".dlg-active").removeClass('dlg-active');
        self.dialog.addClass('dlg-active').show();
        if (self.options.modal) self.cover.show();
        return self;
    };
    //close the dialog
    self.close = function () {
    	try {
    		if(time_over != null){
    			clearTimeout(time_over);
    		}
		} catch (e) {
		}
    	
        if (self.cover) self.cover.remove();
        self.dialog.remove();
        $('.dlg-active').removeClass('dlg-active');
        getActiveDialog().addClass('dlg-active');
        return self;
    };
    //add buttons to the footer
    self.addBtn = function (name, callback, cssClass) {
        if (typeof callback !== 'function') callback = null;
        cssClass = cssClass || 'dlg-btn';
        var btn = $("<a/>", { text: name, click: callback, href: '#', 'class': cssClass }).appendTo(self.dialog.find('.dlg-footer'));
        if(self.options.bootstrap && !btn.is(".btn")) btn.addClass('btn');
        return self;
    };
    //maximum
    //@nocache:是否不缓存调整前窗体的大小
    self.maxWindow = function (nocache) {
        if (!self.options.maximum || maxed) return;
        self.dialog.find('.dlg-max').hide();
        var body = getDialogBody(),
            width = body.width(),
            height = body.height(),
            header = self.dialog.find('.dlg-header'),
            footer = self.dialog.find('.dlg-footer'),
            top = 3,
            left = 5;
        if (!nocache) body.data('before', { width: width, height: height, position: self.dialog.offset() });
        body.css({
            width: function () { return $(window).width() - parseInt(body.css('padding-left'),10) - parseInt(body.css('padding-right'),10) - self.options.dragMargin.left - self.options.dragMargin.right - left - 10 },
            height: function () { return $(window).height() - header.height() - footer.height()
                    - parseInt(header.css('padding-top'),10) - parseInt(header.css('padding-bottom'),10)
                    - parseInt(footer.css('padding-top'),10) - parseInt(footer.css('padding-bottom'),10)
                    - parseInt(body.css('padding-top'),10) - parseInt(body.css('padding-bottom'),10)
                    - self.options.dragMargin.top - self.options.dragMargin.bottom
                    + top;
                }
        });
        self.dialog.css({ top: top, left: left, margin: 0 })
            .find('.dlg-restore').css({ display: 'inline-block' })
            .end().find('.dlg-resize').hide();
        setWeightHeight();
        maxed = true;
        return self;
    };
    //restore
    self.restoreWindow = function () {
        if (!maxed) return;
        self.dialog.find('.dlg-restore').hide();
        var body = getDialogBody(),
            original = body.data('before');
        if (!original){
            body.css({ width: self.options.width, height: self.options.height });
            setDialogCentral();
        }else{
            body.css({ width: original.width, height: original.height });
            self.dialog.css({ left: original.position.left, top: original.position.top });
        }
        self.dialog.find('.dlg-max').css({ display: 'inline-block' }).end().find('.dlg-resize').show();
        setWeightHeight();
        maxed = false;
        return self;
    }

    return self;
}