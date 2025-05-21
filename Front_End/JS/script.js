let currentIndex = 0;

function updateIndicators() {
    const indicators = document.querySelectorAll('.indicator');
    indicators.forEach((indicator, index) => {
        indicator.classList.toggle('active', index === currentIndex);
    });
}

function updateSlide() {
    const track = document.querySelector('.carousel-track');
    
    // força reflow (recalcula layout) antes de aplicar a transformação
    track.offsetHeight; 
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

// Pré-carregamento com fallback para garantir que tudo está visível
window.onload = () => {
    // Aguarda o próximo frame para garantir layout estável
    requestAnimationFrame(() => {
        updateSlide(); // primeira exibição
        setTimeout(() => {
            setInterval(() => moveSlide(1), 3000); // inicia rotação
        }, 100); // pequeno delay para estabilizar layout
    });
};
