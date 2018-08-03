$(function(){
	var lastScrollTop = 0;

	 $(window).scroll(function() {    

	 if($(window).width() > 768){
		    var wS = $(this).scrollTop();

//	$('.sidebar').sticky('.main-div'); // Initialize the sticky scrolling on an item
		    var leftBar=$(".leftbarCelebrity").outerHeight();
		  var hT = $('.sidebar').offset().top,
		   // hH = $('#scroll-to').outerHeight(),
		    he = $('.sidebar').outerHeight();
		  //console.log(he);
		  if(leftBar>he){
		    wH = $(window).height();
		   var headerStyle=$('.header-style').height();
		   var a=he+(headerStyle-hT);
		   var b=wS+headerStyle+200;
		   var c=he-(wH-50);
		   c=-Math.abs(c);
		if(b>a){
				console.log('you have scrolled to the h1!');
				$(".sidebar").css({'position':'fixed','left':$('.sidebar').offset().left,'overflow-y':'hidden','overflow-x':'hidden','top':c});
		   }else if(wS <= lastScrollTop){
			   $(".sidebar").css({'position':'relative','left':'auto','overflow-y':'hidden','overflow-x':'hidden','top':'auto'});
		   }
		   lastScrollTop = wS;
	 }
	 }
//	$(window).scroll(function() {
//		   if($(window).scrollTop() + $(window).height() == $(document).height() && $(window).width() >= 768) {
//		     //  $('.sidebar').css({'position':'relative','left':0});
//               $('.sidebar').css({'height':$(window).height() - ($('.header-style').height()+100)});
//
//		   }
//		});
	 });
});

//(function($, window, undefined) {
//
// "use strict";
//
// /**
//  * Sticky Element constructor
//  * @param elm
//  * @param par
//  * @param options
//  * @constructor
//  */
// var Sticky = function(elm, par, options) {
//     this.element = elm;
//     this.parent = par;
//     this._frozen = false;
//     this.options = $.extend({
//         animate: false,
//         useFixed: true,
//         animTime: 300
//     }, options);
//     this.init();
// };
//
// Sticky.prototype.init = function() {
//     this.element.addClass("sticky-scroll");
//     this.update();
// };
//
// Sticky.prototype.update = function() {
//     //This will handle any resizing of the container the sticky scroll is in and update the boundaries if necessary
//     this.setBoundaries();
//     this.moveIt();
// };
//
// Sticky.prototype.moveIt = function() {
//     // This will decide whether to move the stickied item
//    // var scrollTop = $(window).scrollTop();
//     var height = this.element.outerHeight(true);
//     var realStop = this._stop - height;
//     var hT = $('#scroll-to').offset().top,
//     hH = $('#scroll-to').outerHeight(),
//     wH = $(window).height(),
//     wS = $(window).scrollTop();
//    // alert("after"+(hT-wH) , wS);
//  console.log((hT-wH) , wS);
// 
//   //  if (this._parentHeight - this._offset > height && !this._frozen) {
//    	 if (wS > (hT+hH-wH)){
//    		  // alert('you have scrolled to the h1!');
//             this.element.css({'position':'fixed','left':this.element.offset().left,'overflow-y':'hidden','overflow-x':'hidden'});
//
//    		 }
//       //  if (scrollTop >= this._start && scrollTop <= realStop) {
//        	// alert($(window).height()-200);
////alert($(window).height() - ($('.header-style').height()+35));
//            // if(this.options.useFixed){
//              //     this.element.css({'position':'fixed','left':this.element.offset().left,'overflow-y':'hidden','overflow-x':'hidden'});
//           //      this.element.css({'position':'fixed','left':this.element.offset().left,'overflow-y':'auto','height':$(window).height() - ($('.header-style').height()+35),'overflow-x':'hidden'});
//          //   } else {
//                // this.updateOffset(scrollTop - this._start);
//          //   }
//       //  } else {
//       //      this.element.css({'position':'fixed','left':this.element.offset().left,'overflow-y':'hidden','overflow-x':'hidden'});
//
//         //   this.element.css({'position':'fixed','left':this.element.offset().left,'overflow-y':'auto','height':$(window).height() - ($('.header-style').height()+35),'overflow-x':'hidden'});
//           /*   if (scrollTop < this._start) {
//               //  this.updateOffset(0);
//             } else if (scrollTop > realStop) {
//                 this.updateOffset(this._parentHeight - height - this._offset);
//             } */
//        // }
//    // }
// };
//
// Sticky.prototype.setBoundaries = function() {
//     // This will set the boundaries the stickied item can move between and it's left position
//     this._offset = this.element.position().top;
//     this._start = this.parent.offset().top + this._offset;
//     this._parentHeight = this.parent.outerHeight();
//     this._stop = this._start + this._parentHeight - this._offset;
// };
//
// /**
//  * Update Stickied Element's offset
//  * @param yOffset
//  */
// Sticky.prototype.updateOffset = function(yOffset) {
//     if (!this._lastPosition !== yOffset) {
//         // This moves the item
//         if (this.animate) {
//             this.element.stop().animate({
//                 'top': yOffset
//             }, this.animTime);
//         } else {
//             this.element.css('top', yOffset);
//         }
//         this._lastPosition = yOffset;
//     }
// };
//
// Sticky.prototype.toggleFreeze = function() {
//     // This will freeze the stickied item in place wherever it is
//     this._frozen = !this._frozen;
// };
//
// $.fn.sticky = function(par, options) {
//     var method, args, ret = false;
//     if (typeof options === "string") {
//         args = [].slice.call(arguments, 0);
//     }
//
//     this.each(function() {
//         var self = $(this);
//         var parent = par;
//         if (parent) {
//             parent = self.parent().closest(parent);
//         } else {
//             parent = self.parent();
//         }
//         parent.css({'position':'relative'}); // Set parent position to relative
//         self.css({'position':'relative'}); // Set item position to relative
//         var instance = self.data("stickyInstance");
//
//         if (instance && options) {
//             if (typeof options === "object") {
//                 ret = $.extend(instance.options, options);
//             } else if (options === "options") {
//                 ret = instance.options;
//             } else if (typeof instance[options] === "function") {
//                 ret = instance[options].apply(instance, args.slice(1));
//             } else {
//                 throw new Error("Sticky Element has no option/method named " + method);
//             }
//         } else {
//             instance = new Sticky(self, parent, options || {});
//             self.data("stickyInstance", instance);
//             $.fn.sticky._instances.push(instance);
//         }
//     });
//     return ret || this;
// };
//
// $.fn.sticky._instances = [];
//
// $(window).on({
//     'resize': function(e) {
//         // Update the position/offset changed on resize and move
//         $.each($.fn.sticky._instances, function() {
//             this.update();
//         });
//     },
//     'scroll': function() {
//         // Move all those suckers on scroll
//         $.each($.fn.sticky._instances, function() {
//             if (!this._frozen) {
//                 this.moveIt();
//             }
//         });
//     }
// });
//}(jQuery, window));