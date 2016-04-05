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
        <div class="title-list">Mes ${listType}</div>
        <div id="listTweet" class="container-fluid">
            <#if listType="followers">
                <#assign followers=user.followers>
                <#list followers as follower>
                    <div class="tweet">${follower.login}</div>
                </#list>
            <#elseif listType="following">
                <#assign following=user.following>
                <#list following as aFollowing>
                    <div class="tweet">${aFollowing.login}</div>
                </#list>
            </#if>
        </div>
    </div>
</body>
</html>
