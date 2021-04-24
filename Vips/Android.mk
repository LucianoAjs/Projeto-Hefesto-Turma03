LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_MODULE_TAGS := optional
LOCAL_STATIC_ANDROID_LIBRARIES := \
	androidx.appcompat_appcompat \
	com.google.android.material_material \
	androidx-constraintlayout_constraintlayout \
	androidx.gridlayout_gridlayout \
	androidx.recyclerview_recyclerview


# LOCAL_RESOURCE_DIR := $(LOCAL_PATH)/res
# LOCAL_PROGUARD_ENABLED := disabled

# include $(BUILD_PREBUILD)
# include $(CLEAR_VARS)
# LOCAL_MODULE_TAGS := optional
# LOCAL_UNINSTALLABLE_MODULE := true
# LOCAL_MODULE_PATH := $(TARGET_OUT_APPS)
LOCAL_PACKAGE_NAME := Vips
LOCAL_SRC_FILES := $(call all-java-files-under, src)
LOCAL_CERTIFICATE := platform
LOCAL_PROGUARD_ENABLED := disabled
LOCAL_PRIVILEGED_MODULE := TRUE
# LOCAL_RESOURCE_DIR := $(LOCAL_PATH)/res
# LOCAL_STATIC_JAVA_LIBRARIES := libaraty
# LOCAL_STATIC_JAVA_LIBRARIES += android-support-v4
# LOCAL_STATIC_JAVA_LIBRARIES := android-support-v7-appcompat
# LOCAL_STATIC_JAVA_LIBRARIES += android-support-v7-gridlayout
# LOCAL_STATIC_JAVA_LIBRARIES += android-support-v13
# ALLOW_MISSING_DEPENDENCIES
LOCAL_SDK_VERSION := current

include $(BUILD_PACKAGE)

include $(call all-makefiles-under, $(LOCAL_PATH))