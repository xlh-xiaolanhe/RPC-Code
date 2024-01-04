// 这是一个thrift文件

namespace java com.xiaolanhe

struct User{
 1:string name,
 2:string password
}

service UserService{
   User queryUserByNameAndPassword(1:string name,2:string password)
   void save(1:User user)
}