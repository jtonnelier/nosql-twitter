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
        <div id="postTweet">
            <form action="./tweet" class="form" role="form" method="POST">
                <textarea id="tweetMsg" class="col-xs-12" placeholder="Tweet your thought" name="tweetMsg"
                          maxlength="140"></textarea>
                <input type="submit" class="btn btn-info pull-right" value="Poster">
            </form>
        </div>

        <div id="myStat">
            Tweets persos : <span id="nbTweets">${user.tweets?size}</span>,
            Nombre de followers : <span id="nbFollower">${user.followers?size}</span>,
            Nombre de personnes followed : <span id="nbFollowing">${user.following?size}</span>
        </div>

        <div id="listTweet" class="container-fluid">
            <#assign tweets=user.tweets>
            <#list tweets as tweet>
                <div class="tweet">${tweet}</div>
            </#list>
        </div>
    </div>
</body>
</html>
