use jcandystore;

create table if not exists SUPPLIER (
    SUPP_ID int not null, 
    SUPP_NAME varchar(80) null,
    STATUS varchar(2) not null,
    ADDR1 varchar(80) null,
    ADDR2 varchar(80) null,
    CITY varchar(80) null,
    SUPP_STATE varchar(80) null,
    ZIP varchar(5) null,
    PHONE varchar(80) null,  
primary key (SUPP_ID) );

create table if not exists SIGNON (
    USERNAME varchar(25) not null,
    PASSWORD varchar(25)  not null,  
primary key (USERNAME) );

create table if not exists ACCOUNT (
    USER_ID varchar(80) not null,
    EMAIL varchar(80) not null,
    FIRST_NAME varchar(80) not null,
    LAST_NAME varchar(80) not null,
    STATUS varchar(2)  null,
    ADDR1 varchar(80) not null,
    ADDR2 varchar(40) null,
    CITY varchar(80) not  null,
    ACNT_STATE varchar(80) not null,
    ZIP varchar(20) not null,
    COUNTRY varchar(20) not null,
    PHONE varchar(80) not null,
primary key (USER_ID) );

create table if not exists PROFILE (
    USER_ID varchar(80) not null,
    LANG_PREF varchar(80) not null,
    FAVGATEGORY varchar(30),
    MYLIST_OPT bool,
    BANNER_OPT bool,
primary key (USER_ID) );

create table if not exists BANNERDATA (
    FAVGATEGORY varchar(80) not null,
    BANNER_NAME varchar(255)  null, 
primary key (FAVGATEGORY) );

create table if not exists ORDERS (
      ORDER_ID int not null,
      USER_ID varchar(80) not null,
      ORDER_DATE date not null,
      SHIP_ADDR1 varchar(80) not null,
      SHIP_ADDR2 varchar(80) null,
      SHIP_CITY varchar(80) not null,
      SHIP_STATE varchar(80) not null,
      SHIP_ZIP varchar(20) not null,
      SHIP_COUNTRY varchar(20) not null,
      BILL_ADDR1 varchar(80) not null,
      BILL_ADDR2 varchar(80)  null,
      BILL_CITY varchar(80) not null,
      BILL_STATE varchar(80) not null,
      BILL_ZIP varchar(20) not null,
      BILL_COUNTRY varchar(20) not null,
      COURIER varchar(80) not null,
      TOTAL_PRICE decimal(10,2) not null,
      BILL_TO_FIRSTNAME varchar(80) not null,
      BILL_TO_LASTNAME varchar(80) not null,
      SHIP_TO_FIRSTNAME varchar(80) not null,
      SHIP_TO_LASTNAME varchar(80) not null,
      CREDIT_CARD varchar(80) not null,
      EXPR_DATE varchar(7) not null,
      CARD_TYPE varchar(80) not null,
      LOCALE varchar(80) not null,
primary key (ORDER_ID) );

create table if not exists ORDER_STATUS (
      ORDER_ID int not null,
      LINE_NUM int not null,
      ORDER_TIMESTAMP date not null,
      STATUS varchar(2) not null,
primary key (ORDER_ID, LINE_NUM) );

create table if not exists LINE_ITEM (
      ORDER_ID int not null,
      LINE_NUM int not null,
      ITEM_ID varchar(10) not null,
      QUANTITY int not null,
      UNIT_PRICE decimal(10,2) not null,
primary key (ORDER_ID, LINE_NUM) );

create table if not exists CATEGORY (
	CAT_ID varchar(10) not null,
	CAT_NAME varchar(80) null,
	DESCRIPTION varchar(255) null,
primary key (CAT_ID) );

create table if not exists PRODUCT (
    PROD_ID varchar(10) not null,
    PROD_CATEGORY varchar(10) not null,
    PROD_NAME varchar(80) null,
    DESCRIPTION varchar(255) null,
primary key (PROD_ID) );

alter table PRODUCT 
	add index PRODUCT_CAT(PROD_CATEGORY);

alter table PRODUCT 
	add index PRODUCT_NAME(PROD_NAME);

alter table CATEGORY 
	add index IX_CATEGORY_PRODUCT(CAT_ID);

alter table PRODUCT add foreign key (PROD_CATEGORY) 
         references CATEGORY(CAT_ID) 
         on delete restrict 
         on update restrict;

create table if not exists ITEM (
    ITEM_ID varchar(10) not null,
    PRODUCT_ID varchar(10) not null,
    LIST_PRICE decimal(10,2) null,
    UNIT_COST decimal(10,2) null,
    SUPPLIER int null,
     STATUS varchar(2) null,
     ATTR1 varchar(80) null,
     ATTR2 varchar(80) null,
     ATTR3 varchar(80) null,
    ATTR4 varchar(80) null,
    ATTR5 varchar(80) null,
primary key (ITEM_ID) );

-- create table if not exists ITEM (
--     ITEM_ID varchar(10) not null,
--     PRODUCT_ID varchar(10) not null,
--     LIST_PRICE decimal(10,2) null,
--     UNIT_COST decimal(10,2) null,
--     SUPPLIER int null,
--     STATUS varchar(2) null,
--     ATTR1 varchar(80) null,
--     ATTR2 varchar(80) null,
--     ATTR3 varchar(80) null,
--     ATTR4 varchar(80) null,
--     ATTR5 varchar(80) null,
-- primary key (ITEM_ID) );

alter table ITEM 
	add index ITEM_PROD(PRODUCT_ID);

alter table ITEM add foreign key (PRODUCT_ID) 
         references PRODUCT(PROD_ID) 
         on delete restrict 
         on update restrict;

alter table ITEM add foreign key (SUPPLIER) 
         references SUPPLIER(SUPP_ID) 
         on delete restrict 
         on update restrict;

create table if not exists INVENTORY (
    ITEM_ID varchar(10) not null,
    QUANTITY int not null,
primary key (ITEM_ID) );

create table if not exists A_SEQUENCE (
    SEQ_NAME    varchar(30)  not null,
    NEXT_ID     int          not null,
primary key (SEQ_NAME) );
