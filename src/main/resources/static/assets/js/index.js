// Nice Select
$(document).ready(function() {
    $('select').niceSelect();
});

// Owl Carousel
$('.owl-carousel').owlCarousel({
    loop:true,
    dots: false,
    autoplay:true,
    autoplayTimeout: 3000,
    animateIn: 'slideInRight',
    smartSpeed: 1000,
    responsive:{
        0:{
            items: 4
        }
    }
});