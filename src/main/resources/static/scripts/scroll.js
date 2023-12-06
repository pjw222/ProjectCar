
        window.onscroll = function () {
            scrollFunction();
        };

        function scrollFunction() {
            var scrollToTopBtn = document.getElementById("upimg");


            if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
                upimg.style.display = "block";
            } else {
                upimg.style.display = "none";
            }
        }

        function scrollToTop() {
            document.body.scrollTop = 0; 
            document.documentElement.scrollTop = 0; 
        }