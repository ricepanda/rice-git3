CREATE TABLE EN_RTE_NODE_INSTN_T (
	RTE_NODE_INSTN_ID              NUMBER(19) NOT NULL,
	DOC_ID						   NUMBER(19) NOT NULL,
	RTE_NODE_ID                    NUMBER(19) NOT NULL,
	BRCH_ID						   NUMBER(19),
	PROC_RTE_NODE_INSTN_ID		   NUMBER(19),
	ACTV_IND					   NUMBER(1) DEFAULT 0 NOT NULL,
    CMPLT_IND					   NUMBER(1) DEFAULT 0 NOT NULL,
    INIT_IND					   NUMBER(1) DEFAULT 0 NOT NULL,
	DB_LOCK_VER_NBR	               NUMBER(8) DEFAULT 0,
	CONSTRAINT EN_RTE_NODE_INSTN_T_PK PRIMARY KEY (RTE_NODE_INSTN_ID) USING INDEX
)
/