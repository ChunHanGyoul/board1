# board1
Board with java&amp;jsp

you need to make Database with OracleDB.

CREATE TABLE "JBOARD" 
   (	"NUM" NUMBER, 
	"NAME" VARCHAR2(30), 
	"PASS" VARCHAR2(30), 
	"SUBJECT" VARCHAR2(60), 
	"CONTENT" VARCHAR2(3000), 
	"ATTACHED_FILE" VARCHAR2(60), 
	"ANSWER_NUM" NUMBER, 
	"ANSWER_LEV" NUMBER, 
	"ANSWER_SEQ" NUMBER, 
	"READ_COUNT" NUMBER, 
	"WRITE_DATE" DATE
   ) ;
   
you can manage your db username, password and url in METE-INF/context.xml

thank you.
