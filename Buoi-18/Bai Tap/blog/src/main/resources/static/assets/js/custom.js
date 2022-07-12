jQuery(document).ready(function ($) {
    "use strict";

    $(window).scroll(function () {
        let scroll = $(window).scrollTop();
        let box = $('.header-text').height();
        let header = $('header').height();

        if (scroll >= box - header) {
            $("header").addClass("background-header");
        } else {
            $("header").removeClass("background-header");
        }
    });

    //Active menu
    const menuEle = document.querySelectorAll("#navbarResponsive li");

    const activeMenu = () => {
      let path = window.location.pathname;

      Array.from(menuEle).map(e => e.classList.remove("active"));
      menuEle.forEach(e => {
          if (e.firstElementChild.getAttribute("href") === path) {
              e.classList.add("active");
          }
      })
    }
    activeMenu();

    const pageNumberEl = document.querySelector(".page-numbers li");

    const activePagingNumber = () => {
      let path = window.location.pathname;
    }


    // if ($('.owl-clients').length) {
    //     $('.owl-clients').owlCarousel({
    //         loop: true,
    //         nav: false,
    //         dots: true,
    //         items: 1,
    //         margin: 30,
    //         autoplay: false,
    //         smartSpeed: 700,
    //         autoplayTimeout: 6000,
    //         responsive: {
    //             0: {
    //                 items: 1,
    //                 margin: 0
    //             },
    //             460: {
    //                 items: 1,
    //                 margin: 0
    //             },
    //             576: {
    //                 items: 3,
    //                 margin: 20
    //             },
    //             992: {
    //                 items: 5,
    //                 margin: 30
    //             }
    //         }
    //     });
    // }
    //
    // if ($('.owl-banner').length) {
    //     $('.owl-banner').owlCarousel({
    //         loop: true,
    //         nav: true,
    //         dots: true,
    //         items: 3,
    //         margin: 10,
    //         autoplay: false,
    //         smartSpeed: 700,
    //         autoplayTimeout: 6000,
    //         responsive: {
    //             0: {
    //                 items: 1,
    //                 margin: 0
    //             },
    //             460: {
    //                 items: 1,
    //                 margin: 0
    //             },
    //             576: {
    //                 items: 1,
    //                 margin: 10
    //             },
    //             992: {
    //                 items: 3,
    //                 margin: 10
    //             }
    //         }
    //     });
    // }

});
