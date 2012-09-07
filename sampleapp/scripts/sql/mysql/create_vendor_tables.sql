--
-- Copyright 2005-2012 The Kuali Foundation
--
-- Licensed under the Educational Community License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
--
-- http://www.opensource.org/licenses/ecl2.php
--
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.
--

CREATE  TABLE IF NOT EXISTS `pur_vndr_hdr_t` (
  `VNDR_HDR_GNRTD_ID` DECIMAL(10,0) NOT NULL DEFAULT '0' ,
  `OBJ_ID` VARCHAR(36) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL ,
  `VER_NBR` DECIMAL(8,0) NOT NULL DEFAULT '1' ,
  `VNDR_TYP_CD` VARCHAR(4) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL ,
  `VNDR_TAX_NBR` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `VNDR_TAX_TYP_CD` VARCHAR(4) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `VNDR_OWNR_CD` VARCHAR(4) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `VNDR_OWNR_CTGRY_CD` VARCHAR(4) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `VNDR_FWT_BEG_DT` DATETIME NULL DEFAULT NULL ,
  `VNDR_FWT_END_DT` DATETIME NULL DEFAULT NULL ,
  `VNDR_W9_RCVD_IND` VARCHAR(1) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `VNDR_W8_BEN_RCVD_IND` VARCHAR(1) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `VNDR_DEBRD_IND` VARCHAR(1) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `VNDR_FRGN_IND` VARCHAR(1) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  PRIMARY KEY (`VNDR_HDR_GNRTD_ID`) ,
  UNIQUE INDEX `PUR_VNDR_HDR_TC0` (`OBJ_ID` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin
/

CREATE  TABLE IF NOT EXISTS `pur_ownr_typ_t` (
  `VNDR_OWNR_CD` VARCHAR(4) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL DEFAULT '' ,
  `OBJ_ID` VARCHAR(36) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL ,
  `VER_NBR` DECIMAL(8,0) NOT NULL DEFAULT '1' ,
  `VNDR_OWNR_DESC` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `VNDR_OWNR_CTGRY_ALLW_IND` VARCHAR(1) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `DOBJ_MAINT_CD_ACTV_IND` VARCHAR(1) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL ,
  PRIMARY KEY (`VNDR_OWNR_CD`) ,
  UNIQUE INDEX `PUR_OWNR_TYP_TC0` (`OBJ_ID` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin
/

CREATE  TABLE IF NOT EXISTS `pur_pmt_term_typ_t` (
  `VNDR_PMT_TERM_CD` VARCHAR(5) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL DEFAULT '' ,
  `OBJ_ID` VARCHAR(36) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL ,
  `VER_NBR` DECIMAL(8,0) NOT NULL DEFAULT '1' ,
  `VNDR_DSCT_DUE_TYP_DESC` VARCHAR(20) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `VNDR_DSCT_DUE_NBR` DECIMAL(2,0) NULL DEFAULT NULL ,
  `VNDR_PMT_TERM_PCT` DECIMAL(6,3) NULL DEFAULT NULL ,
  `VNDR_NET_DUE_TYP_DESC` VARCHAR(20) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `VNDR_NET_DUE_NBR` DECIMAL(2,0) NULL DEFAULT NULL ,
  `VNDR_PMT_TERM_DESC` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `DOBJ_MAINT_CD_ACTV_IND` VARCHAR(1) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL ,
  PRIMARY KEY (`VNDR_PMT_TERM_CD`) ,
  UNIQUE INDEX `PUR_PMT_TERM_TYP_TC0` (`OBJ_ID` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin
/

CREATE  TABLE IF NOT EXISTS `pur_vndr_addr_t` (
  `VNDR_ADDR_GNRTD_ID` DECIMAL(10,0) NOT NULL DEFAULT '0' ,
  `OBJ_ID` VARCHAR(36) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL ,
  `VER_NBR` DECIMAL(8,0) NOT NULL DEFAULT '1' ,
  `VNDR_HDR_GNRTD_ID` DECIMAL(10,0) NOT NULL ,
  `VNDR_DTL_ASND_ID` DECIMAL(10,0) NOT NULL ,
  `VNDR_ADDR_TYP_CD` VARCHAR(4) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL ,
  `VNDR_LN1_ADDR` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `VNDR_LN2_ADDR` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `VNDR_CTY_NM` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `VNDR_ST_CD` VARCHAR(2) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `VNDR_ZIP_CD` VARCHAR(20) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `VNDR_CNTRY_CD` VARCHAR(2) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `VNDR_ATTN_NM` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `VNDR_ADDR_INTL_PROV_NM` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `VNDR_ADDR_EMAIL_ADDR` VARCHAR(90) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `VNDR_B2B_URL_ADDR` VARCHAR(90) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `VNDR_FAX_NBR` VARCHAR(40) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `VNDR_DFLT_ADDR_IND` VARCHAR(1) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `DOBJ_MAINT_CD_ACTV_IND` VARCHAR(1) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL ,
  PRIMARY KEY (`VNDR_ADDR_GNRTD_ID`) ,
  UNIQUE INDEX `PUR_VNDR_ADDR_TC0` (`OBJ_ID` ASC) ,
  INDEX `PUR_VNDR_ADDR_TI1` (`VNDR_HDR_GNRTD_ID` ASC, `VNDR_DTL_ASND_ID` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin
/

CREATE  TABLE IF NOT EXISTS `pur_vndr_dflt_addr_t` (
  `VNDR_DFLT_ADDR_GNRTD_ID` DECIMAL(10,0) NOT NULL DEFAULT '0' ,
  `OBJ_ID` VARCHAR(36) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL ,
  `VER_NBR` DECIMAL(8,0) NOT NULL DEFAULT '1' ,
  `VNDR_ADDR_GNRTD_ID` DECIMAL(10,0) NOT NULL ,
  `VNDR_CMP_CD` VARCHAR(2) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `DOBJ_MAINT_CD_ACTV_IND` VARCHAR(1) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL ,
  PRIMARY KEY (`VNDR_DFLT_ADDR_GNRTD_ID`) ,
  UNIQUE INDEX `PUR_VNDR_DFLT_ADDR_TC0` (`OBJ_ID` ASC) ,
  INDEX `PUR_VNDR_DFLT_ADDR_TI1` (`VNDR_ADDR_GNRTD_ID` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin
/

CREATE  TABLE IF NOT EXISTS `pur_vndr_dtl_t` (
  `VNDR_HDR_GNRTD_ID` DECIMAL(10,0) NOT NULL DEFAULT '0' ,
  `VNDR_DTL_ASND_ID` DECIMAL(10,0) NOT NULL DEFAULT '0' ,
  `OBJ_ID` VARCHAR(36) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL ,
  `VER_NBR` DECIMAL(8,0) NOT NULL DEFAULT '1' ,
  `VNDR_PARENT_IND` VARCHAR(1) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `VNDR_NM` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL ,
  `DOBJ_MAINT_CD_ACTV_IND` VARCHAR(1) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL ,
  `VNDR_INACTV_REAS_CD` VARCHAR(4) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `VNDR_DUNS_NBR` VARCHAR(9) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `VNDR_PMT_TERM_CD` VARCHAR(5) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `VNDR_SHP_TTL_CD` VARCHAR(4) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `VNDR_SHP_PMT_TERM_CD` VARCHAR(4) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `VNDR_CNFM_IND` VARCHAR(1) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `VNDR_PRPYMT_IND` VARCHAR(1) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `VNDR_CCRD_IND` VARCHAR(1) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `VNDR_MIN_ORD_AMT` DECIMAL(7,2) NULL DEFAULT NULL ,
  `VNDR_URL_ADDR` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `VNDR_SOLD_TO_NM` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `VNDR_RMT_NM` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `VNDR_RSTRC_IND` VARCHAR(1) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `VNDR_RSTRC_REAS_TXT` VARCHAR(60) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `VNDR_RSTRC_DT` DATETIME NULL DEFAULT NULL ,
  `VNDR_RSTRC_PRSN_ID` VARCHAR(40) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `VNDR_SOLD_TO_GNRTD_ID` DECIMAL(10,0) NULL DEFAULT NULL ,
  `VNDR_SOLD_TO_ASND_ID` DECIMAL(10,0) NULL DEFAULT NULL ,
  `VNDR_1ST_LST_NM_IND` VARCHAR(1) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `COLLECT_TAX_IND` VARCHAR(1) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL DEFAULT 'Y' ,
  PRIMARY KEY (`VNDR_HDR_GNRTD_ID`, `VNDR_DTL_ASND_ID`) ,
  UNIQUE INDEX `PUR_VNDR_DTL_TC0` (`OBJ_ID` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin
/