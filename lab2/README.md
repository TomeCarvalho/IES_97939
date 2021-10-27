2.3 - result of:
PS C:\Users\ToméCarvalho> curl http://localhost:8081/greeting?name=Saul%20Goodman
StatusCode        : 200
StatusDescription :
Content           : {"id":11,"content":"Hello, Saul Goodman!"}
RawContent        : HTTP/1.1 200
                    Transfer-Encoding: chunked
                    Keep-Alive: timeout=60
                    Connection: keep-alive
                    Content-Type: application/json
                    Date: Wed, 27 Oct 2021 11:12:12 GMT

                    {"id":11,"content":"Hello, Saul Goodma...
Forms             : {}
Headers           : {[Transfer-Encoding, chunked], [Keep-Alive, timeout=60], [Connection, keep-alive], [Content-Type, application/json]...}
Images            : {}
InputFields       : {}
Links             : {}
ParsedHtml        : System.__ComObject
RawContentLength  : 42