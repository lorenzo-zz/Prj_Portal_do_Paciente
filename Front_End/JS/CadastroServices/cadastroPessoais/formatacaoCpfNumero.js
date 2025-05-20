document.getElementById('cpf').addEventListener('input', function () {
        this.value = this.value.replace(/\D/g, '');
      });

      document.getElementById('telefone').addEventListener('input', function () {
        this.value = this.value.replace(/\D/g, '');
      });