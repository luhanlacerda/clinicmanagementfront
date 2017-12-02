$(window).scroll(
	function(e) {
        e(document).ready(function() {
            e(".rotate-btn").on("click", function() {
                e("#" + e(this).attr("data-card")).toggleClass("flipped")
            })
        })
    }(jQuery)
);