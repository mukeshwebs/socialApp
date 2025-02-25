```

1. Login Endpoint (/auth/login)
bash
Copy
curl -X POST "http://localhost:8081/auth/login" \
     -H "Content-Type: application/json" \
     -d '{
           "username": "john_doe",
           "password": "password123"
         }'

2. Signup Endpoint (/auth/signup)
bash
Copy
curl -X POST "http://localhost:8081/auth/signup" \
     -H "Content-Type: application/json" \
     -d '{
           "firstName": "John",
           "lastName": "Doe",
           "username": "john_doe",
           "password": "password123",
           "email": "john_doe@example.com"
         }'

3. Save Comment (/comments/save)
bash
Copy
curl -X POST "http://localhost:8081/comments/save" \
     -H "Content-Type: application/json" \
     -d '{
           "post": {"postID": 101},
           "user": {"userID": "john_doe"},
           "content": "Great post! Really enjoyed it.",
           "imageURL": "http://example.com/image.jpg"
         }'

4. Find Comments by Post ID (/comments/find/byPost)
bash
Copy
curl -X GET "http://localhost:8081/comments/find/byPost?postID=101"

5. Save Follow (/follow/save)
bash
Copy
curl -X POST "http://localhost:8081/follow/save" \
     -H "Content-Type: application/json" \
     -d '{
           "follower": {"userID": "john_doe"},
           "userTarget": {"userID": "jane_doe"}
         }'

6. Delete Follow (/follow/delete)
bash
Copy
curl -X DELETE "http://localhost:8081/follow/delete?followerID=john_doe&targetID=jane_doe"

7. Like Comment (/likeComment/saves)
bash
Copy
curl -X POST "http://localhost:8081/likeComment/saves?userID=john_doe&commentID=55"

8. Unlike Comment (/likeComment/delete)
bash
Copy
curl -X DELETE "http://localhost:8081/likeComment/delete?userID=john_doe&commentID=55"

9. Like Post (/likePost/saves)
bash
Copy
curl -X POST "http://localhost:8081/likePost/saves?postID=101"

10. Unlike Post (/likePost/delete)
bash
Copy
curl -X DELETE "http://localhost:8081/likePost/delete?postID=101"

11. Find Post by Post ID (/posts/find/{postID})
bash
Copy
curl -X GET "http://localhost:8081/posts/find/101"

12. Submit Post (/posts/saves)
bash
Copy
curl -X POST "http://localhost:8081/posts/saves" \
     -H "Content-Type: application/json" \
     -d '{
           "user": {"userID": "john_doe"},
           "content": "This is a new post about Spring Boot.",
           "createDay": "2025-02-26T12:00:00"
         }'

13. Get User Details (/users/{userID})
bash
Copy
curl -X GET "http://localhost:8081/users/john_doe"

14. Update User (/users/update)
bash
Copy
curl -X POST "http://localhost:8081/users/update" \
     -H "Content-Type: application/json" \
     -d '{
           "userID": "john_doe",
           "firstName": "John",
           "lastName": "Doe",
           "address": "123 Main St, Springfield, IL"
         }'

15. Search User by Keyword (/users/search)
bash
Copy
curl -X GET "http://localhost:8081/users/search?keyWord=john"

Notes:
The commands are all using the localhost:8081 address since you mentioned the server runs on port 8081.
Dummy data such as "john_doe", "password123", "Great post! Really enjoyed it.", and others are placeholders. You can replace them with actual values as needed.
These curl commands are



```
