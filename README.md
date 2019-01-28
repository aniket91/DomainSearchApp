# DomainSearchApp
Simple spring and maven based web application to index and search domains.

## Build Instructions
1. Clone this repository
2. Run `mvn clean install`
3. Download tomcat runtime
4. Copy war file(DomainSearchApp.war) from target folder to webapps of tomcat installation dir
5. Start tomcat
6. Go to http://localhost:8080/DomainSearchApp/search.html
7. type in three or more chars for autocomplete to show up
8. Click on submit.
9. If single result is returned it would open in new tab (make sure popups are enabled for localhost)
10. If multiple results are returned it would be displayed below search field. Each result is an hyperlink.

## Few important files -
1. pom.xml - Has all maven dependencies required by the project like spring, gson etc
2. WebContent/WEB-INF/web.xml - Web application configuration goes here
3. WebContent/WEB-INF/domainsearcher-servlet.xml - Dispacher servlet configuration goes here
4. WebContent/WEB-INF/JSP/search.jsp - Single jsp page with text field that supports auto complete
5. src/com/domainsearch/controller/SearchDomain.java - controller for spring application
6. src/com/domainsearch/index/DomainIndexer.java - Indexer service that interacts between controller and actual index impl
7. src/com/domainsearch/index/Trie.java - Actual indexing implementation
8. src/top-1m.csv - domains data set


## Other Notes
1. Project needs to be deployed on a tomcat container
2. Project uses Java/JSP/Jquery/Javascript/HTML 

## Known limitations & furture scope
1. Indexing implementation does not handle all chars. It just handles chars/numbers and a .
2. API to fetch matched domain results is not authenticated
3. UI can be made better with frameworks like bootstrap
4. Results returned by API can be paginated to avoid latency due to large result set returns 

