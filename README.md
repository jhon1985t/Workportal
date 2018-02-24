# Workportal
Bizagi test application users list and accept or pending requests

compileSdkVersion 27
minSdkVersion 16

This project was developed with mvp design and clean architecture, use of Cloud Database Firebase, 
Widgets injection with ButterKnife.

Description 

package application
 - WorkPortalApplication : initialize injection modules, and application context
 
 package base
 - BaseActivity : abstract class that load intern methods to be extends or implements on called class
 - BasePresenter : inject baseview interface to be implemented
 
 package constants 
 - Utils : load global urls and preferences key
 
 package di/components
 - ApplicationComponent : load the modules to be injected into the class
 - UserComponent : load the modules to be injected into the class
 
 package di/modules
 - ApplicationModule : provide global access to application resources
 - UserModule : provide global interface to be implemented on class
 
 package modules/details
 - DetailActivity : class the load the selected user 
 
 package modules/home
 - adapter/MainAdapter : adapter that received loaded data from Users Class and paint into the layout
 - MainActivity : initialize the recyclerview and the adapter
 - SettingActivity : filter the load by aprroved or ignored state
 
 package mvp/model
 - Storage : SQLDATABASE instance we use it to save and update on restart connection settings
 - Users : Users model data
 
 package mvp/presenter
 - UserPresenter : logic business
 
 package mvp/view
 BaseView : empty interface
 MainVies : extends baseview and implement views to be loaded
 
 package utilities
 NetWorkUtilities : Class that register access to connection settings and get response 
 
 
 
 

