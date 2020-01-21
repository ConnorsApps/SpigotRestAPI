# SpigotRestAPI

A simple http api for spigot minecraft servers.

The plugin is meant to be a simple and easy to maintain as possible, as such it implements very little.

I hope for it to provide the bare minimum that is needed to implement most kinds of systems

It mostly allows you to get player info via a http request.

# Config

```yaml
port: 8765                     # the port the http server should listen on
cors:                          # CORS Configuration
  enabled: false               # If is Enabled
  multiple: false              # If you had multiple origins
  origin: 'null'               # The single orign; Use '*' as wild card or 'http://youriste.com'

# If 'multiple' is enabled

  origins:
  - http://yoursite.dom
  - http://mysite.dom
  - http://theysite.dom
```

# Response

All responses are formatted like this.

``` js
{
    "status": "Okay" / "Fail",
    "data" : <response object>
}
```

# Data Endpoints

### /players `GET`
Get a list of online players and their basic info
<br>
`response = array of player objects`

### /players/:uuid `GET`
Get info for a specific player uuid
<br>
`:uuid =  a player uuid`
<br>
`response = a player object`
<br>

### /worlds `GET`
Get a list of worlds available in the server
<br>
`response = array of world objects`

### /worlds/:name `GET`
Get info for a specific world uuid
<br>
`:name =  a world name`
<br>
`response = a world object`
<br>