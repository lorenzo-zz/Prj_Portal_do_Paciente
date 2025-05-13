// 800 x 450

let currentIndex = 0;
        const images = document.querySelectorAll(".carousel img");
        const indicators = document.querySelectorAll(".indicator");

        function updateIndicators(index) {
            indicators.forEach((ind, i) => {
                ind.classList.toggle("active", i === index);
            });
        }

        function showNextImage() {
            currentIndex = (currentIndex + 1) % images.length;
            updateCarousel();
        }

        function goToSlide(index) {
            currentIndex = index;
            updateCarousel();
        }

        function updateCarousel() {
            const offset = -currentIndex * 100;
            document.querySelector(".carousel-images").style.transform = `translateX(${offset}%)`;
            updateIndicators(currentIndex);
        }

        setInterval(showNextImage, 3000);