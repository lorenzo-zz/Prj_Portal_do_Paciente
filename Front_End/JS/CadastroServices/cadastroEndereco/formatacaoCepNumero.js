document.getElementById('cep').addEventListener('input', function () {
        this.value = this.value.replace(/\D/g, '');
      });

      document.getElementById('numero').addEventListener('input', function () {
        this.value = this.value.replace(/\D/g, '');
      });