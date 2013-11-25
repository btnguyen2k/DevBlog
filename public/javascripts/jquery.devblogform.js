;(function ( $, window, document, undefined ) {
    var pluginName = "devblogform",
        defaults = {
        };
    
    function Plugin( element, options ) {
        this.element = element;
        this.$elem = $(this.element);
        this.options = $.extend( {}, defaults, options );
        this._defaults = defaults;
        this._name = pluginName;
        this.init();
    }
    
    Plugin.prototype = {
        init: function() {
        	var formEls = this.$elem.find('input[data-devblogform]');
        	formEls.each(function(){
        		var $this1 = $(this);
        		$this1.on('blur', function(){
        			//$(this).next('.devblogform-infomsg').html('&nbsp;');
        			$(this).next('.devblogform-infomsg').hide();
        			$(this).parent('.devblogform-row').removeClass('devblogform-row-active');
        		});
        		$this1.on('focus', function(){
        			//$(this).next('.devblogform-infomsg').html($this1.attr('data-helper'));
        			$(this).next('.devblogform-infomsg').show();
        			$(this).parent('.devblogform-row').addClass('devblogform-row-active');
        		});
        	});    
        }
    };
    
    // A really lightweight plugin wrapper around the constructor,
    // preventing against multiple instantiations
    $.fn[pluginName] = function ( options ) {
        return this.each(function () {
            if (!$.data(this, "plugin_" + pluginName)) {
                $.data(this, "plugin_" + pluginName, new Plugin( this, options ));
            }
        });
    };
})( jQuery, window, document );
