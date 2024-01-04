namespace java com.xiaolanhe

struct User{
    required string name,
    optional i32 age,
    optional string password = '123456'
}

enum SEASON{
  SPRING = 1,
  SUMMER = 2
}

exception MyException{
  1:i32 errorCode,
  2:string message
}