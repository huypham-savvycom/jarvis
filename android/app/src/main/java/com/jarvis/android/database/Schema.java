package com.jarvis.android.database;

/**
 * All schema of database
 * <p/>
 */
public class Schema {

    private static String TAG = Schema.class.getName();

    // Table Album
    public static String ALBUM_NAME_TABLE = "album";
    public static String ALBUM_ID = "AlbumID";
    public static String ALBUM_TYPE_ID = "AlbumTypeID";
    public static String CREATE_ALBUM_TABLE = "CREATE TABLE album (AlbumID INTEGER PRIMARY KEY UNIQUE, AlbumTypeID TEXT)";
    // Table Business
    public static String BUSINESS_NAME_TABLE = "business";
    public static String BUSINESS_ID = "BusinessID";
    public static String SUBCATEGORY_ID = "SubCategoryID";
    public static String NAME = "Name";
    public static String ADDRESS_FIRST = "Address1";
    public static String ADDRESS_SECOND = "Address2";
    public static String CITY = "City";
    public static String STATE = "State";
    public static String ZIPCODE = "Zip";
    public static String PHONE = "Phone";
    public static String WEBSITE = "Website";
    public static String EMAIL = "Email";
    public static String DESCRIPTION = "Description";
    public static String CREATE_BUSINESS_TABLE = "CREATE TABLE business (BusinessID INTEGER PRIMARY KEY UNIQUE, SubCategoryID INTEGER,  Name TEXT, Address1 TEXT, Address2 TEXT, City TEXT, State TEXT, Zip TEXT, Phone TEXT, Website TEXT, Email TEXT, Description TEXT)";
    // Table Contact
    public static String CONTACT_NAME_TABLE = "contact";
    public static String PHONE_HASH = "PhoneHash";
    public static String CUSTOMER_ID = "CustomerID";
    public static String LOGIN_ID = "LoginID";
    //    public static String FIRSTNAME = "FirstName";
//    public static String LASTNAME = "LastName";
    public static String IS_ZAKA_USER = "isZakaUser";
    public static String IS_LOCAL_USER = "isLocalUser";
    public static String IMAGE_ID = "ImageID";
    public static String SELFCONFIRMED = "SelfConfirmed";
    public static String FRIENDCONFIRMED = "FriendConfirmed";
    public static String CREATE_CONTACT_TABLE = "CREATE TABLE contact (PhoneHash TEXT PRIMARY KEY UNIQUE, CustomerID INTEGER, LoginID TEXT, Name TEXT, FirstName TEXT, LastName TEXT, Phone TEXT, isZakaUser INTEGER, isLocalUser INTEGER, City TEXT, State TEXT, ImageID INTEGER, Active INTEGER, SelfConfirmed INTEGER, FriendConfirmed INTEGER)";
    // Table Customer Profiles
    public static String CUSTOMER_PROFILE_NAME_TABLE = "customerProfile";
    public static String PHONE_CELL = "PhoneCell";
    public static String PHONE_BUS = "PhoneBus";
    public static String EMAIL_VERIFIED = "EmailVerified";
    public static String HIDE_ALL_VENDORS = "HideAllVendors";
    public static String CREATE_CUSTOMER_PROFILES_TABLE = "CREATE TABLE customerProfile (CustomerID INTEGER PRIMARY KEY UNIQUE, ImageID INTEGER, LoginID TEXT, Name TEXT,FirstName TEXT, LastName Text, Address1 TEXT, Address2 Text, City TEXT, State TEXT, Zip TEXT, PhoneCell TEXT, PhoneBus TEXT, EmailVerified INTEGER, HideAllVendors INTEGER)";
    // Table Deal
    public static String DEAL_NAME_TABLE = "deal";
    public static String CAMPAIGN_ID = "CampaignID";
    public static String MERCHANT_ID = "MerchantID";
    public static String MERCHANT_NAME = "MerchantName";
    public static String BUSINESS_NAME = "BusinessName";
    public static String TITLE = "Title";
    public static String DATE_START = "DateStart";
    public static String DATE_END = "DateEnd";
    public static String CREATE_AT = "createdAt";
    public static String UPDATE_AT = "updatedAt";
    public static String STATUS = "Status";
    public static String CLOSE = "Closed";
    public static String HIDDEN = "Hidden";
    public static String PROMO_CODE_TYPE = "PromoCodeType";
    public static String PROMO_CODE = "PromoCode";
    public static String RECURRING_TYPE = "RecurringType";
    public static String CLAIMED_DATE = "ClaimedDate";
    public static String NEXT_CLAIM_TIME = "NextClaimTime";
    public static String CREATE_DEAL_TABLE = "CREATE TABLE deal (CampaignID INTEGER PRIMARY KEY UNIQUE, ImageID INTEGER, MerchantID INTEGER, MerchantName TEXT, BusinessID INTEGER, BusinessName TEXT, Title TEXT, Description TEXT, DateStart REAL, DateEnd REAL, createdAt REAL, Status TEXT, Closed INTEGER, Hidden INTEGER, PromoCodeType TEXT, PromoCode TEXT, RecurringType TEXT, ClaimedDate REAL, NextClaimTime REAL)";
    // Table Main Categories
    public static String MAIN_CATEGORY_NAME_TABLE = "mainCategories";
    public static String MAIN_CATEGORY = "MainCategoryID";
    public static String MAIN_CATEGORY_GROUP = "GroupCategory";
    public static String MAIN_CATEGORY_GPTYPE = "GooglePlaceType";
    public static String MAIN_CATEGORY_SUB = "SubCategory";
    public static String CREATE_MAIN_CATEGORY_TABLE = "CREATE TABLE mainCategories (MainCategoryID INTEGER PRIMARY KEY UNIQUE, Name TEXT, GroupCategory TEXT, GooglePlaceType TEXT, SubCategory TEXT)";
    public static String VENDER_CATEGORY_NAME_TABLE = "vendorCategory";
    public static String VENDER_CATEGORY_ID = "VendorCategoryID";
    public static String CREATE_VENDER_CATEGORY_TABLE = "CREATE TABLE vendorCategory (VendorCategoryID INTEGER, MerchantID INTEGER REFERENCES merchant(MerchantID), ImportID INTEGER REFERENCES nonZakaMerchant (ImportID), CategoryID INTEGER NOT NULL)";
    // Table Merchant
    public static String MERCHANT_NAME_TABLE = "merchant";
    public static String YT_VIDEO_ID = "YTVideoID";
    public static String PUBLIC = "Public";
    public static String SHOW_CUSTOMER_COUNT = "ShowCustomerCount";
    public static String ACTIVE = "Active";
    public static String CATEGORY_ID = "CategoryID";
    public static String GPID = "GPID";
    public static String BULLETINBOARD_COUNT = "BulletinBoardCount";
    public static String FOLLOWING_NEWSFEED = "FollowingNewsfeed";
    public static String FOLLOWING_NOTIFICATIONS = "FollowingNotifications";
    public static String BULLETINBOARD = "BulletinBoard";
    public static String WEB_BUTTON_LABEL = "WebButtonLabel";
    public static String WEB_BUTTON_URL = "WebButtonURL";
    public static String LAT = "Lat";
    public static String LONG = "Long";

    public static String CREATE_MERCHANT_TABLE = "CREATE TABLE merchant (MerchantID INTEGER PRIMARY KEY UNIQUE, ImageID INTEGER, Name TEXT, FirstName TEXT, LastName TEXT, Address1 TEXT, Address2 TEXT, City TEXT, State TEXT, Zip TEXT, PhoneCell TEXT, PhoneBus TEXT, BusinessID INTEGER, BusinessName TEXT, Description TEXT, Website TEXT, YTVideoID TEXT, Public INTEGER, ShowCustomerCount INTEGER, Active INTEGER, CategoryID INTEGER, BulletinBoardCount INTEGER, FollowingNewsfeed INTEGER, FollowingNotifications INTEGER, BulletinBoard TEXT, WebButtonLabel TEXT, WebButtonURL TEXT,Lat REAL, Long REAL)";
    // Table MESSAGE
    public static String MESSAGE_NAME_TABLE = "message";
    public static String MESSAGE_ID = "MessageID";
    public static String TO_USER_READ = "ToUserRead";
    public static String TO_USER_ID = "ToUserID";
    public static String TO_USER_NAME = "ToUserName";
    public static String FROM_USER_ID = "FromUserID";
    public static String FROM_USER_NAME = "FromUserName";
    public static String BODY = "Body";
    public static String BOX = "Box";
    public static String CREATE_MESSAGE_TABLE = "CREATE TABLE message (MessageID INTEGER PRIMARY KEY UNIQUE, ToUserRead INTEGER, ToUserID INTEGER, ToUserName TEXT, FromUserID INTEGER, FromUserName TEXT, Title TEXT, Body TEXT, Box TEXT, createdAt REAL)";
    // Table Non Zaka Merchant
    public static String NON_ZAKA_MERCHANT_NAME_TABLE = "nonZakaMerchant";
    public static String CREATE_NON_ZAKA_MERCHANT_TABLE = "CREATE TABLE nonZakaMerchant (ImportID INTEGER PRIMARY KEY UNIQUE,Name TEXT, FirstName TEXT, LastName TEXT, Address1 Text, Address2 Text, City TEXT, State TEXT, Zip TEXT, PhoneCell TEXT, PhoneBus TEXT, BusinessID INTEGER, BusinessName TEXT, Description TEXT, Website TEXT, Public INTEGER, Active INTEGER, CategoryID INTEGER, GPID TEXT, WebButtonLabel TEXT, WebButtonURL TEXT)";
    // Table Notification
    public static String NOTIFICATION_NAME_TABLE = "notification";
    public static String NOTIFICATION_ID = "NotificationID";
    public static String NOTIFICATION_TYPE_ID = "NotificationTypeID";
    public static String USER_ID = "UserID";
    public static String ALERT = "Alert";
    public static String PAYLOAD = "Payload";
    public static String READ = "Read";
    public static String CREATE_NOTIFICATION_TABLE = "CREATE TABLE notification (NotificationID INTEGER PRIMARY KEY UNIQUE, NotificationTypeID INTEGER, UserID INTEGER, Alert TEXT, Payload TEXT, Read INTEGER, createdAt REAL)";

    // Table Referral
    // api/customer/pulse
    public static String REFERRAL_NAME_TABLE = "referral";
    public static String REFERRAL_ID = "ReferralID";
    public static String REFERER_CUSTOMER_ID = "RefererCustomerID";
    public static String REFERER_NAME = "RefererName";
    public static String REFEREE_CUSTOMER_ID = "RefereeCustomerID";
    public static String REFEREE_NAME = "RefereeName";
    public static String REFEREE_PHONE = "RefereePhone";
    public static String MESSAGE = "Message";
    public static String VENDOR_TYPE = "VendorType";
    public static String VENDOR_ID = "VendorID";
    public static String VENDOR_NAME = "VendorName";
    public static String VENDOR_PROFILE_IMAGE_ID = "VendorProfileImageID";
    public static String VENDOR_CITY = "VendorCity";
    public static String VENDOR_STATE = "VendorState";
    public static String VENDOR_HOURS = "VendorHours";
    public static String VENDOR_RATING = "VendorRating";
    public static String VENDOR_DESCRIPTION = "VendorDescription";
    public static String FRIENDS_VENDOR = "FriendsWithVendor";
    public static String VENDOR_LAT = "Lat";
    public static String VENDOR_LONG = "Long";
    public static String IMPORT_ID = "ImportID"; // does not exist in api/customer/pulse
    public static String CREATE_REFERRAL_TABLE = "CREATE TABLE referral (ReferralID INTEGER PRIMARY KEY UNIQUE, RefererCustomerID INTEGER, RefererName TEXT, RefereeCustomerID INTEGER, RefereeName TEXT, RefereePhone TEXT, Status TEXT, Message TEXT,  " +
            "VendorName TEXT, VendorID INTEGER, VendorType TEXT, VendorProfileImageID INTEGER, VendorCity TEXT, VendorState TEXT, VendorRating DOUBLE, VendorHours TEXT, VendorDescription TEXT, createdAt REAL, updatedAt REAL, Lat DOUBLE, Long DOUBLE, FriendsWithVendor TEXT)";

    // Table SubCategories
    public static String SUB_CATEGORY_NAME_TABLE = "subCategories";
    public static String MAIN_CATEGORY_ID = "MainCategoryID";
    public static String CREATE_SUB_CATEGORY_TABLE = "CREATE TABLE subCategories (SubCategoryID INTEGER PRIMARY KEY UNIQUE, MainCategoryID INTEGER, Name TEXT)";
    // Table User
    public static String USER_NAME_TABLE = "user";
    public static String CREATE_USER_TABLE = "CREATE TABLE user (userID INTEGER, customerID INTEGER, loginID TEXT, tokenValue TEXT, Role Text)";
    public static String DB_INFO_TABLE = "db_info";
    public static String DB_INFO_KEY = "key";
    public static String DB_INFO_VALUE = "value";
    public static String CONTACT_BOOK_HASH_KEY = "CONTACT_BOOK_HASH_KEY";
    public static String CREATE_DB_INFO_TABLE = "CREATE TABLE db_info (key TEXT PRIMARY KEY UNIQUE, value TEXT)";
    // Table friendRequest
    public static String DB_FRIEND_REQUEST_TABLE = "friendRequest";
    public static String TYPE = "Type";
    public static String CREATE_FRIEND_REQUEST_TABLE = "CREATE TABLE friendRequest (UserID INTEGER PRIMARY KEY UNIQUE, ImageID INTEGER, Name TEXT, FirstName TEXT, LastName TEXT, City TEXT, State TEXT, Type INTEGER)";


    // Table friendRequest
    public static String NEWS_FEED_TABLE = "newsfeed";
    public static String NEWS_FEED_PATH = "Path";
    public static String NEWS_FEED_PARAMS = "Params";
    public static String NEWS_FEED_BODY = "Body";
    public static String CREATE_NEWS_FEED_TABLE = "CREATE TABLE newsfeed (Path TEXT, Params TEXT, Body TEXT)";

}
