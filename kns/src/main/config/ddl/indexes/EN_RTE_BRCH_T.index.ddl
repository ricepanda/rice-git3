CREATE INDEX EN_RTE_BRCH_TI1 ON EN_RTE_BRCH_T (BRCH_NM)
/
CREATE INDEX EN_RTE_BRCH_TI2 ON EN_RTE_BRCH_T (PARNT_RTE_BRCH_ID)
/
CREATE INDEX EN_RTE_BRCH_TI3 ON EN_RTE_BRCH_T (INIT_RTE_NODE_INSTN_ID)
/
CREATE INDEX EN_RTE_BRCH_TI4 ON EN_RTE_BRCH_T (SPLT_RTE_NODE_INSTN_ID)
/
CREATE INDEX EN_RTE_BRCH_TI5 ON EN_RTE_BRCH_T (JOIN_RTE_NODE_INSTN_ID)
/
