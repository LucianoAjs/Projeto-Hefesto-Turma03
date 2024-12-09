# Projeto Hefesto - Turma 03

O **Projeto Hefesto** é um Programa de Capacitação e Desenvolvimento de Aplicações para Dispositivos Móveis, desenvolvido no **CDTI/EST-UEA** (Centro de Desenvolvimento de Tecnologias e Inovação da Escola Superior de Tecnologia da Universidade do Estado do Amazonas).

---

## Projeto: Implementação de um Controle para Restringir Ligações no Celular

### **Tema**: Adicionar ao AOSP um controle sobre os números discados.  
### **Objetivo**:  
Restringir o uso do Dialer (Discador) no celular em dispositivos Android, bloqueando ligações para números que não estejam em uma lista pré-definida. Esse controle é ideal para ambientes corporativos.

---

## Tutorial para Rodar o Projeto no AOSP v10-r47

### **Passo 1: Configuração do Framework**

Na pasta `framework`, copie os seguintes arquivos para os respectivos caminhos:

- **`IPhonebookService.aidl`**  
  `/aosp/frameworks/base/core/java/android/app/`

- **`PhonebookService.java`**  
  `/aosp/frameworks/base/services/core/java/com/android/server/`

- **`PhonebookServiceHelper.java`**  
  `/aosp/frameworks/base/services/core/java/com/android/server/`

- **`PhonebookManager.java`**  
  `/aosp/frameworks/base/core/java/android/app/`

- **`Context.java`**  
  `/aosp/frameworks/base/core/java/android/content/`

- **`Android.bp`**  
  `/aosp/frameworks/base/`

- **`SystemServer.java`**  
  `/aosp/frameworks/base/services/java/com/android/server/`

- **`SystemServiceRegistry.java`**  
  `/aosp/frameworks/base/core/java/android/app/`

---

### **Passo 2: Configuração da Aplicação VIPS**

● Copie a pasta **`Vips`** para o seguinte caminho:  
   `/aosp/packages/apps/`

● Adicione o nome do aplicativo no `PRODUCT_PACKAGES` no arquivo `handheld_system.mk` localizado em:  
   `/aosp/build/target/product/`

---

### **Passo 3: Configuração do Aplicativo Dialer**

Substitua o arquivo **`NewOutgoingCallIntentBroadcaster.java`** pelo arquivo presente na pasta `dialer`. O caminho para substituição é:  
`/aosp/packages/services/Telecomm/src/com/android/server/telecom/`

---

### **Passo 4: Build do Projeto**

● Atualize a API com o comando:  
```bash
make update-api
```
● Atualize os stubs com o comando:
```bash
make api-stubs-docs-update-current-api
```
● Compile o projeto:
```bash
make -j4
```

### **Passo 5: Configuração do Emulador**

  ● Execute o emulador com o SELinux desativado usando o seguinte comando:

  ```bash
  emulator -selinux disabled
  ```

  ● Caso queira limpar os dados do emulador, utilize também a flag -wipe-data:

 ```bash
  emulator -wipe-data -selinux disabled
  ```


Observação:
Como o SELinux não foi configurado, o serviço estará indisponível por padrão. Desabilitar o SELinux é necessário para tornar o serviço funcional.
