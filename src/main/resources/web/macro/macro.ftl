<#macro importCSSJS>
    <#-- Ceci est archi mega degeu, pas le choix vu que le navigateur n'a pas acces au fichiers externe -->
    <style>
       <#include "bootstrap-theme.min.ftl">
       <#include "bootstrap.min.ftl">
       <#include "style.ftl">
    </style>
    <script>
        <#include "bootstrap.min.js.ftl">
        <#include "jquery-1.11.2.min.js.ftl">
    </script>
</#macro>

<#macro header>
    <div class="customHeader">
        <div class="page-header">
            <h2>Low-Cost Twitter (mais bon il fonctionne...)</h2>

            <div class="pull-right menuHeader">
                <div class="glyphicon glyphicon-user">
                ${user.login}
                </div>
            </div>
        </div>
    </div>
</#macro>

<#macro menu>
    <div id="menu">
        <ul><div id=titleMenu">Menu</div>
            <li class="itemMenu">
                <a href="index">Mes tweets</a>
            </li>
            <li class="form itemMenu" role="form">
                <form action="./search" class="form" role="form" method="POST">
                    <input id="hashtag" name="hashtag" type="text" class="form-control" placeholder="Search">
                    <input id="postSearch" type="submit" >
                </form>
            </li>
            <li class="itemMenu">
                <a href="list?listType=followers">Followers</a>
            </li>
            <li class="itemMenu">
                <a href="list?listType=following">Following</a>
            </li>
        </ul>
    </div>
</#macro>