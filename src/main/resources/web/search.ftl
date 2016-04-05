<#import "macro/macro.ftl" as m>
<!doctype html>

<html>
<head>
    <title>Redis Twitter</title>
    <@m.importCSSJS/>
</head>
<body>
    <@m.header/>

    <@m.menu/>


    <div class="container">
        <div class="title-list">Recherche tweets: ${hashtag}</div>
        <div id="listTweet" class="container-fluid">
            <#list tweets as tweet>
                <div class="tweet">${tweet}</div>
            </#list>
        </div>
    </div>
</body>
</html>
