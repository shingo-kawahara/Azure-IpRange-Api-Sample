# Overview
Download the XML file from official Microsoft Azure Datacenter IP Ranges and output only the specified region in Text format.  
(公式のMicrosoft Azure Datacenter IP RangesからXMLファイルをダウンロードして、指定したリージョンのものだけをText形式で出力します。)

# Usage
Deploy to Azure Functions and use it. By using the information below, you can obtain information on the Azure Datacenter IP Range of the corresponding region by issuing a GET request.  
(Azure Functionsにデプロイして利用します。 利用する側は、以下の情報を元にGETリクエストを発行することで、該当リージョンのAzure Datacenter IP Rangeの情報を取得することができます。)

* functionsKey
* region

## Usage for PowerShell

```
Invoke-WebRequest -Uri "https://[Your Function App].azurewebsites.net/api/iprange" -Method GET -Body @{code="$functionsKey";region="$region"} -UseBasicParsing | select-ExpandProperty Content
```

# Output Example

```
13.73.232.0/21
20.39.176.0/21
20.189.192.0/18
20.190.141.0/25
23.98.56.0/24
23.100.104.0/21
40.74.64.0/18
40.74.128.0/20
40.79.209.0/24
40.80.56.0/21
40.81.176.0/20
40.87.204.0/22
40.90.137.0/27
40.90.142.208/28
40.90.156.0/26
```
