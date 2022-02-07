# Analise De Dados
Analisando dados de um arquivo e emitindo relatório analítico.

## Fluxo principal
* Certificar que os diretórios existam 
* Entrar em loop infinito
    * Ler lista de arquivos com extensão .dat no path ~/data/in 
    * Interar pelos arquivos existentes no diretório
    * Interar por cada linha do arquivo
        * Identificar o padrão de cada linha (vendedor, cliente, vendas)
        * Instanciar objeto de acordo com padrão identificado
    * Calcular quantidade de clientes
    * Calcular quantidade de vendedores
    * Identificar venda mais cara
    * Totalidar vendas por vendedor
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
  001çCPFçNameçSalary
  
Para cada linha  do arquivo compatível com um dos padrões acima um objeto com o tipo específico é instanciado

## Saida de dados

Para cada arquivo lido, uma saida de dados é gerada no diretório  %HOMEPATH%/data/out.

Cada arquivo de saida contém:
● Quantidade de clientes no arquivo de entrada
● Quantidade de vendedor no arquivo de entrada
● ID da venda mais cara
● O pior vendedor

## Resolução

* Para lidar com busca, criação e exclusão de arquivos e diretórios utilizei a biblioteca  java.nio.file que facilita 
  todo o processo
  
* Utilizei o pattern de Builder para instanciar cada objeto de acordo com o tipo (vendedor, cliente e venda)

* Um objeto FileData deve conter 3 arrays de dados (vendedor, cliente e vendas) e conseguir fazer os calculos 
  necessários para gerar o arquivo de saída. 