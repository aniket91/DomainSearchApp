# DomainSearchApp
Simple spring and maven based web application to index and search domains.

Few important files -
1. pom.xml - Has all maven dependencies required by the project like spring, gson etc
2. WebContent/WEB-INF/web.xml - Web application configuration goes here
3. WebContent/WEB-INF/domainsearcher-servlet.xml - Dispacher servlet configuration goes here
4. WebContent/WEB-INF/JSP/search.jsp - Single jsp page with text field that supports auto complete
5. src/com/domainsearch/controller/SearchDomain.java - controller for spring application
6. src/com/domainsearch/index/DomainIndexer.java - Indexer service that interacts between controller and actual index impl
7. src/com/domainsearch/index/Trie.java - Actual indexing implementation
8. src/top-1m.csv - domains data set


Other Notes
1. Project needs to be deployed on a tomcat container
2. Project uses Java/JSP/Jquery/Javascript/HTML 




