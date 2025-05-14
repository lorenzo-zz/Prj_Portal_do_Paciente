let currentIndex = 0;
        function updateIndicators() {
            const indicators = document.querySelectorAll('.indicator');
            indicators.forEach((indicator, index) => {
                indicator.classList.toggle('active', index === currentIndex);
            });
        }
        function moveSlide(direction) {
            const track = document.querySelector('.carousel-track');
            const items = document.querySelectorAll('.carousel-item');
            const lastIndex = items.length - 1;

            if (currentIndex === lastIndex && direction === 1) {
                currentIndex = 0; // Volta direto para o primeiro slide
            } else {
                currentIndex = (currentIndex + direction + items.length) % items.length;
            }

            track.style.transform = `translateX(-${currentIndex * 100}%)`;
            updateIndicators();
        }
        function setSlide(index) {
            currentIndex = index;
            moveSlide(0);
        }
        setInterval(() => moveSlide(1), 3000);