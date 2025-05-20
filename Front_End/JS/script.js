let currentIndex = 0;

function updateIndicators() {
    const indicators = document.querySelectorAll('.indicator');
    indicators.forEach((indicator, index) => {
        indicator.classList.toggle('active', index === currentIndex);
    });
}

function updateSlide() {
    const track = document.querySelector('.carousel-track');
    track.style.transform = `translateX(-${currentIndex * 100}%)`;
    updateIndicators();
}

function moveSlide(direction) {
    const items = document.querySelectorAll('.carousel-item');
    currentIndex = (currentIndex + direction + items.length) % items.length;
    updateSlide();
}

function setSlide(index) {
    currentIndex = index;
    updateSlide();
}

function toggleMenu() {
    const rightLinks = document.querySelector('.right_links');
    rightLinks.classList.toggle('active');
}

document.addEventListener('DOMContentLoaded', () => {
    updateSlide(); // garante slide inicial
    setInterval(() => moveSlide(1), 3000);
});
