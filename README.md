# Projeto Hefesto Turma 03
> O Projeto Hefesto é Programa de Capacitação e Desenvolvimento de Aplicações para Dispositivos Móveis, a ser desenvolvido no CDTI/EST-UEA (Centro de Desenvolvimento de Tecnologias e Inovação da Escola Superior de Tecnologia da Universidade do Estado do Amazonas).

# ● Projeto: Implementação de um controle para restringir as ligações do celular.
# ● Tema: Adicionar ao AOSP um controle sobre os números discados.
# ● Objetivo: Bloquear no AOSP o uso do Dialer (Discador) no celular, impedindo de realizar ligações para números fora de uma lista pré-definida para o uso em ambiente corporativo.

# Tutorial de como rodar o projeto com o AOSP v-10 r-47.

Passo 1: Framework
Na pasta framework contém 8 arquivos. Copie e cole os 4 arquivos nos
seguintes paths:

● IPhonebookService.aidl
/aosp/frameworks/base/core/java/android/app/

● PhonebookService.java
/aosp/frameworks/base/services/core/java/com/android/server/

● PhonebookServiceHelper.java
/aosp/frameworks/base/services/core/java/com/android/server/

● PhonebookManager.java
/aosp/frameworks/base/core/java/android/app/
Modificamos 4 arquivos no framework. Então você deverá substituí-los ou
editá-los.

● Context.java
/aosp/frameworks/base/core/java/android/content/

● Android.bp
/aosp/frameworks/base/

● SystemServer.java
/aosp/frameworks/base/services/java/com/android/server/

● SystemServiceRegistry,java
/aosp/frameworks/base/core/java/android/app/

Passo 2: Vips Application

● Copie o cole a pasta Vips para o path: /aosp/packages/apps/

● Depois adicione o nome do aplicativo no PRODUCT_PACKAGES no arquivo
handheld_system.mk no path: aosp/build/target/product.

Passo 3: Dialer Application
Na pasta dialer copie o arquivo e o substitua no seguinte path:

● NewOutgoingCallIntentBroadcaster.java
/aosp/packages/services/Telecomm/src/com/android/server/telecom/

Passo 4: Build

●Primeiramente devemos atualizar nossa API. Para isso faça o seguinte comando:
make update-api Em seguida atualize os stubs com o comando:

>make api-stubs-docs-update-current-api Por último, dê o comando: make -j4

Passo 5: Emulator

●Como não configuramos o SELinux, o nosso serviço estará indisponível. Para torná-lo disponível mesmo sem configurar o SELinux devemos desabilitar o SELinux
utilizando a flag -selinux. O comando completo a seguir: 

>emulator -selinux disabled

Caso queira limpar os dados do emulador você deve utilizar também a flag -wipe-data. Então o comando ficará assim:

>emulator -wipe-data -selinux disabled
