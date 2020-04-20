# Redis
- Key-Value Type In-memory Data Store  
JVM 위에서 동작하지 않고, Data를 캐싱할 수 있다.  
즉, GC의 관리대상이 아니기 때문에 overhaed가 줄어든다.

## @RedisHash
Class의 Instance가 Redis에 적재될 때, @RedisHash의 인수를  
"key"로 하여 해당 Instance 값을 적재되도록 해준다. 