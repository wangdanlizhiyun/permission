# 动态权限申请库
 
#用法
maven { url 'https://jitpack.io' }
implementation 'com.github.wangdanlizhiyun:permission:1.0.0'
  
  ```
  PermissionUtil.permission(
                  Manifest.permission.WRITE_EXTERNAL_STORAGE,
                  Manifest.permission.ACCESS_FINE_LOCATION,
                  Manifest.permission.ACCESS_COARSE_LOCATION,
                  Manifest.permission.READ_PHONE_STATE
              )
                  .deny {
                      Toast.makeText(this@MainActivity, "deny", Toast.LENGTH_SHORT).show()
                  }
                  .run(Runnable { Toast.makeText(this@MainActivity, "granted", Toast.LENGTH_SHORT).show() })
   
  ```
    
 