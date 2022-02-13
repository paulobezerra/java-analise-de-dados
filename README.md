# Análise De Dados
Analisando dados de um arquivo e emitindo relatório analítico.

## Subindo o RabbitMQ
Para subir o RabbitMQ é necessário ter o docker-compose instalado e executar o comando a seguir na raiz desse projeto: 
`docker-compose up docker-compose.yml`

Pode acessar o seguinte endereço: http://localhost:15672/

Login: guest

Password: guest

## Rodando Producer
A aplicação Producer é responsável por monitorar o diretório ~/data/in, mover os novos arquivos para ~/data/processing, 
e incluir na fila do RabbitMQ para que os arquivos sejam processados.
Para subir esta aplicação, entre no diretório producer e execute o seguinte comando:
* `./mvnw spring-boot:run`

## Rodando Consumer
A aplicação consumer é responsável por processar e gerar o relatório para cada arquivo processado. Para executar esta 
aplicação entre no diretório consumer e execute o seguinte comando:
* `./mvnw spring-boot:run`

### OBS.: O projeto foi implementado utilizando o Intellij Community e basta executar os arquivos Application.java dos projetos para rodar ou debugar via IDE.

## Funcionamento
Com RabbitMQ, Producer e Consumer em execução, o arquivo a ser processado deve ser removido no diretório ~/data/in
colocado na fila para processamento.

Os arquivos que estão em fila são processados e a análise para cada arquivo é colocada em ~/data/out, caso tenha algum 
erro no processamento um arquivo será gerado em ~/datalog 

## Fluxo principal
* Producer:
  * Certificar que os diretórios existam
  * Conecta no RabbitMQ  
    * Loop infinito 
      * Ler lista de arquivos com extensão .dat no path ~/data/in
      * Move arquivos para o ~/data/processing  
      * Registra arquivos no RabbitMQ
  
* Consumer
    * Conecta no RabbitMQ
    * Consome fila
      * Le arquivo em ~/data/process
      * Interar por cada linha do arquivo
        * Identificar o padrão de cada linha (vendedor, cliente, vendas)
        * Instanciar objeto de acordo com padrão identificado
      * Faz análise dos dados 
        * Calcular quantidade de clientes
        * Calcular quantidade de vendedores
        * Identificar venda mais cara
        * Totalidade vendas por vendedor
          * Identificar pior vendedor
      * Gerar arquivo de saída


## Entrada de dados
O sistema identifica e processa arquivos com extensão .dat no diretório %HOMEPATH%/data/in.

O padrão dos dados devem ser:

* Vendedor:
  001çCPFçNameçSalary
  
* Cliente:
  002çCNPJçNameçBusiness Area
  
* Vendas:
  003çSale IDç[Item ID-Item Quantity-Item Price]çSalesman name
  
Para cada linha  do arquivo compatível com um dos padrões acima um objeto com o tipo específico é instanciado

## Saída de dados

Para cada arquivo lido, uma saída de dados é gerada no diretório  %HOMEPATH%/data/out.

Cada arquivo de saída contém:
* Quantidade de clientes no arquivo de entrada
* Quantidade de vendedor no arquivo de entrada
* ID da venda mais cara
* O pior vendedor

## Resolução
* A ideia é ter um único producer observando os arquivos do diretório ~/data/in, a cada novo arquivo a aplicação deve 
  incluir o arquivo na fila e limpá-lo do diretório    

* Podem rodar paralelamente várias instâncias da aplicação producer, permitindo que o processamento dos arquivos possam
ocorrer em paralelo
  
* Como gerenciador da fila utilizei o RabbitMQ rodando em um container Docker integrado a aplicação via biblioteca do 
  Spring. 

* Para lidar com busca, criação e exclusão de arquivos e diretórios utilizei a biblioteca  java.nio.file que facilita 
  todo o processo
  
* Para facilitar a organização da aplicação e testes utilizei uma template básico de projeto do https://start.spring.io/
