// This is for the nav bar hover
$(function () {
    $("#home_img").hover(function () {
        $(".home_img").css("opacity", "1");
    }, function () {
        $(".home_img").css("opacity", "0");
    });
});

$(function () {
    $("#about_img").hover(function () {
        $(".about_img").css("opacity", "1");
        $(".static_img").css("opacity", "0");
    }, function () {
        $(".about_img").css("opacity", "0");
        $(".static_img").css("opacity", "1");
    });
});

$(function () {
    $("#skills_img").hover(function () {
        $(".skills_img").css("opacity", "1");
        $(".static_img").css("opacity", "0");
    }, function () {
        $(".skills_img").css("opacity", "0");
        $(".static_img").css("opacity", "1");
    });
});

$(function () {
    $("#designs_img").hover(function () {
        $(".designs_img").css("opacity", "1");
        $(".static_img").css("opacity", "0");
    }, function () {
        $(".designs_img").css("opacity", "0");
        $(".static_img").css("opacity", "1");
    });
});

$(function () {
    $("#gallery_img").hover(function () {
        $(".gallery_img").css("opacity", "1");
        $(".static_img").css("opacity", "0");
    }, function () {
        $(".gallery_img").css("opacity", "0");
        $(".static_img").css("opacity", "1");
    });
});

$(function () {
    $("#contact_img").hover(function () {
        $(".contact_img").css("opacity", "1");
        $(".static_img").css("opacity", "0");
    }, function () {
        $(".contact_img").css("opacity", "0");
        $(".static_img").css("opacity", "1");
    });
});

// modal

function switchStyle() {
    if (document.getElementById('styleSwitch').checked) {
      document.getElementById('gallery').classList.add("custom");
      document.getElementById('gallerymodal').classList.add("custom");
    } else {
      document.getElementById('gallery').classList.remove("custom");
      document.getElementById('gallerymodal').classList.remove("custom");
    }
}

// design overlay
function on1() {
    document.getElementById("overlay1").style.display = "flex";
  }
  
  function off1() {
    document.getElementById("overlay1").style.display = "none";
  }

  function on2() {
    document.getElementById("overlay2").style.display = "flex";
  }
  
  function off2() {
    document.getElementById("overlay2").style.display = "none";
  }

  function on3() {
    document.getElementById("overlay3").style.display = "flex";
  }
  
  function off3() {
    document.getElementById("overlay3").style.display = "none";
  }

$(document).on('click','.navbar-collapse',function(e) {
    if( $(e.target).is('a') ) {
        $(this).collapse('hide');
    }
});