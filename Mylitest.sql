
-- ----------------------------------------------------------------------------
-- Database Mylitest
-- ----------------------------------------------------------------------------
DROP DATABASE IF EXISTS Mylitest ;
CREATE DATABASE IF NOT EXISTS Mylitest COLLATE utf8_general_ci ;
USE Mylitest;

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------------------------------------------------------
-- Table sys_users
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `sys_users` (
  `user_code` BIGINT NOT NULL,
  `user_name` VARCHAR(120) NOT NULL,
  `user_password` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`user_code`)
)
COLLATE = utf8_general_ci;

-- ----------------------------------------------------------------------------
-- Insert Into sys_users
-- ----------------------------------------------------------------------------
INSERT INTO sys_users VALUES ('1','admin','admin');
INSERT INTO sys_users VALUES ('2','amr','amr');


-- ----------------------------------------------------------------------------
-- Table gen_accounts
-- ----------------------------------------------------------------------------
DROP TABLE IF EXISTS gen_accounts;
CREATE TABLE IF NOT EXISTS gen_accounts (
  account_code VARCHAR(160) NOT NULL,
  account_name VARCHAR(160) NOT NULL,
  group_code VARCHAR(160) NOT NULL,
  group_name VARCHAR(160) NOT NULL,
  is_group VARCHAR(5) NOT NULL,
  open_balance_dr VARCHAR(160) NOT NULL,
  open_balance_cr VARCHAR(160) NOT NULL,
  currency_code BIGINT NULL,
  account_path VARCHAR(250) NULL,
  status TINYINT UNSIGNED NULL,
  final_limit TINYINT UNSIGNED NULL,
  post_on_close TINYINT UNSIGNED NULL,
  required_cost TINYINT UNSIGNED NULL,
  create_user_code BIGINT NULL,
  modify_user_code BIGINT NULL,
  create_time DATETIME(6) NULL,
  modify_time DATETIME(6) NULL,
  FS_Calc_Method TINYINT UNSIGNED NULL,
  PRIMARY KEY (account_code),
  CONSTRAINT FK__gen_accounts__gen_currencies__currency_code
    FOREIGN KEY (currency_code)
    REFERENCES gen_currencies (currency_code)
)
COLLATE = utf8_general_ci;

-- ----------------------------------------------------------------------------
-- Table gen_entry_headers
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `gen_entry_headers` (
  `entry_code` BIGINT NOT NULL,
  `entry_voucher_type` VARCHAR(60) NOT NULL,
  `entry_voucher_code` VARCHAR(60) NOT NULL,
  `entry_date` VARCHAR(60) NULL,
  `entry_note` VARCHAR(60) NULL,
  PRIMARY KEY (`entry_code`)
)
COLLATE = utf8_general_ci;


  
-- ----------------------------------------------------------------------------
-- Table gen_entry_details
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `gen_entry_details` (
  `entry_code` BIGINT NOT NULL,
  `account_code` VARCHAR(160) NOT NULL,
  `account_name` VARCHAR(160) NOT NULL,
  `debit_amount` VARCHAR(60) NULL,
  `credit_amount` VARCHAR(60) NULL,
    CONSTRAINT FK__gen_entry_details__gen_accounts__account_code
    FOREIGN KEY (account_code)
    REFERENCES gen_accounts (account_code),
    CONSTRAINT FK__gen_entry_details__gen_entry_headers__entry_code
    FOREIGN KEY (entry_code)
    REFERENCES gen_entry_headers (entry_code)
)
COLLATE = utf8_general_ci; 



-- ----------------------------------------------------------------------------
-- Table gen_currencies
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `gen_currencies` (
  `currency_code` BIGINT NOT NULL,
  `currency_name_1` VARCHAR(60) NOT NULL,
  `currency_name_2` VARCHAR(60) NOT NULL,
  `small_currency_1` VARCHAR(60) NULL,
  `small_currency_2` VARCHAR(60) NULL,
  `last_price` DECIMAL(19,10) NULL,
  `create_user_code` BIGINT NULL,
  `modify_user_code` BIGINT NULL,
  `createtime` DATETIME(6) NULL,
  `modify_time` DATETIME(6) NULL,
  PRIMARY KEY (`currency_code`)
  )
COLLATE = utf8_general_ci;




-- Insert Into gen_accounts
----------------------------

INSERT INTO gen_accounts (account_code, account_name, group_code, group_name, is_group, open_balance_dr, open_balance_cr) 
VALUES ( '01', 'Assets', 'null',  'null', '1', '0', '0');
INSERT INTO gen_accounts (account_code, account_name, group_code, group_name, is_group, open_balance_dr, open_balance_cr)  
VALUES ( '01-01', 'Furniture', '01', 'Assets', '1', '0', '0');
INSERT INTO gen_accounts (account_code, account_name, group_code, group_name, is_group, open_balance_dr, open_balance_cr) 
VALUES ( '01-02', 'Equipments', '01', 'Assets', '1', '0', '0');
INSERT INTO gen_accounts (account_code, account_name, group_code, group_name, is_group, open_balance_dr, open_balance_cr) 
VALUES ( '01-04', 'Requisites', '01', 'Assets', '1', '0', '0');
INSERT INTO gen_accounts (account_code, account_name, group_code, group_name, is_group, open_balance_dr, open_balance_cr)  
VALUES ( '01-05', 'Cash', '01', 'Assets', '1', '0', '0');
INSERT INTO gen_accounts (account_code, account_name, group_code, group_name, is_group, open_balance_dr, open_balance_cr)  
VALUES ( '01-06', 'Debitors', '01', 'Assets', '1', '0', '0');
INSERT INTO gen_accounts (account_code, account_name, group_code, group_name, is_group, open_balance_dr, open_balance_cr)  
VALUES ( '01-07', 'Notes Receviable', '01', 'Assets', '1', '0', '0');

INSERT INTO gen_accounts (account_code, account_name, group_code, group_name, is_group, open_balance_dr, open_balance_cr) 
 VALUES ( '02', 'Liabilities', 'null',  'null', '1', '0', '0');
INSERT INTO gen_accounts (account_code, account_name, group_code, group_name, is_group, open_balance_dr, open_balance_cr) 
 VALUES ( '02-01', 'StockHolders Equity', '02',  'Liabilities', '1', '0', '0');
INSERT INTO gen_accounts (account_code, account_name, group_code, group_name, is_group, open_balance_dr, open_balance_cr)  
 VALUES ( '02-01-01', 'Capital', '02-01', 'StockHolders Equity', '1', '0', '0');
 INSERT INTO gen_accounts (account_code, account_name, group_code, group_name, is_group, open_balance_dr, open_balance_cr)  
 VALUES ( '02-01-02', 'Withdraws', '02-01', 'StockHolders Equity', '1', '0', '0');
 INSERT INTO gen_accounts (account_code, account_name, group_code, group_name, is_group, open_balance_dr, open_balance_cr) 
 VALUES ( '02-02', 'Short-term Liabilities', '02',  'Liabilities', '1', '0', '0');
INSERT INTO gen_accounts (account_code, account_name, group_code, group_name, is_group, open_balance_dr, open_balance_cr) 
 VALUES ( '02-02-01', 'Creditors', '02-02', 'Short-term Liabilities', '1', '0', '0'); 
INSERT INTO gen_accounts (account_code, account_name, group_code, group_name, is_group, open_balance_dr, open_balance_cr) 
 VALUES ( '02-02-02', 'Notes Payable', '02-02', 'Short-term Liabilities', '1', '0', '0'); 

INSERT INTO gen_accounts (account_code, account_name, group_code, group_name, is_group, open_balance_dr, open_balance_cr)  
VALUES ( '03', 'Revenues', 'null',  'null', '1', '0', '0');
INSERT INTO gen_accounts (account_code, account_name, group_code, group_name, is_group, open_balance_dr, open_balance_cr)  
VALUES ( '03-01', 'Service Revenue', '03', 'Revenues', '1', '0', '0');

INSERT INTO gen_accounts (account_code, account_name, group_code, group_name, is_group, open_balance_dr, open_balance_cr)  
VALUES ( '04', 'Expenses', 'null',  'null', '1', '0', '0');
INSERT INTO gen_accounts (account_code, account_name, group_code, group_name, is_group, open_balance_dr, open_balance_cr)  
VALUES ( '04-01', 'Salaries Expense', '04', 'Expenses', '1', '0', '0');
INSERT INTO gen_accounts (account_code, account_name, group_code, group_name, is_group, open_balance_dr, open_balance_cr)  
VALUES ( '04-02', 'Rent Expense', '04', 'Expenses', '1', '0', '0');
INSERT INTO gen_accounts (account_code, account_name, group_code, group_name, is_group, open_balance_dr, open_balance_cr)  
VALUES ( '04-03', 'Water Expense', '04', 'Expenses', '1', '0', '0');

-- Insert Into gen_entry_headers 
----------------------------------

INSERT INTO gen_entry_headers 
VALUES ( '1', 'Journal Voucher', 'JV-001',  '1/1/2005', 'Investement Owner Cash');
INSERT INTO gen_entry_headers 
VALUES ( '2', 'Journal Voucher', 'JV-002',  '3/1/2005', 'Purchase Furniture Cash');
INSERT INTO gen_entry_headers 
VALUES ( '3', 'Journal Voucher', 'JV-003',  '4/1/2005', 'Purchase Equipments Cash');
INSERT INTO gen_entry_headers 
VALUES ( '4', 'Journal Voucher', 'JV-004',  '7/1/2005', 'Purchase Requisites from Creditors-E3temad ');
INSERT INTO gen_entry_headers 
VALUES ( '5', 'Journal Voucher', 'JV-005',  '9/1/2005', 'Service Cash');
INSERT INTO gen_entry_headers 
VALUES ( '6', 'Journal Voucher', 'JV-006',  '17/1/2005', 'Service for Debitors-Hamy');
INSERT INTO gen_entry_headers 
VALUES ( '7', 'Journal Voucher', 'JV-007',  '20/1/2005', 'Withdraw Cash');
INSERT INTO gen_entry_headers 
VALUES ( '8', 'Journal Voucher', 'JV-008',  '25/1/2005', 'Pay Cash for Creditors-E3temad');
INSERT INTO gen_entry_headers 
VALUES ( '9', 'Journal Voucher', 'JV-009',  '31/1/2005', 'Pay Salaries & Rent & Water Expenses Cash');

-- Insert Into gen_entry_details
--------------------------------- 

INSERT INTO gen_entry_details 
VALUES ( '1', '01-05',  'Cash', '8000' ,'0');
INSERT INTO gen_entry_details 
VALUES ( '1', '02-01-01',  'Capital', '0' ,'8000');

INSERT INTO gen_entry_details 
VALUES ( '2', '01-01',  'Furniture', '500' ,'0');
INSERT INTO gen_entry_details 
VALUES ( '2', '01-05',  'Cash', '0' ,'500');

INSERT INTO gen_entry_details 
VALUES ( '3', '01-02',  'Equipments', '600' ,'0');
INSERT INTO gen_entry_details 
VALUES ( '3', '01-05',  'Cash', '0' ,'600');

INSERT INTO gen_entry_details 
VALUES ( '4', '01-04',  'Requisites', '200' ,'0');
INSERT INTO gen_entry_details 
VALUES ( '4', '02-02-01',  'Creditors', '0' ,'200');  
 
INSERT INTO gen_entry_details 
VALUES ( '5', '01-05',  'Cash', '550' ,'0');
INSERT INTO gen_entry_details 
VALUES ( '5', '03-01',  'Service Revenue', '0' ,'550'); 

INSERT INTO gen_entry_details 
VALUES ( '6', '01-06', 'Debitors', '830' ,'0');
INSERT INTO gen_entry_details 
VALUES ( '6', '03-01',  'Service Revenue', '0' ,'830');

INSERT INTO gen_entry_details 
VALUES ( '7', '02-01-02', 'Withdraws', '300' ,'0');
INSERT INTO gen_entry_details 
VALUES ( '7', '01-05',  'Cash', '0' ,'300');

INSERT INTO gen_entry_details 
VALUES ( '8', '02-02-01',  'Creditors', '80' ,'0');
INSERT INTO gen_entry_details 
VALUES ( '8', '01-05',  'Cash', '0' ,'80');

INSERT INTO gen_entry_details 
VALUES ( '9', '04-01', 'Salaries Expense', '100' ,'0');
INSERT INTO gen_entry_details 
VALUES ( '9', '04-02', 'Rent Expense', '70' ,'0');
INSERT INTO gen_entry_details 
VALUES ( '9', '04-03', 'Water Expense', '300' ,'0');
INSERT INTO gen_entry_details 
VALUES ( '9', '01-05',  'Cash', '0' ,'470');



CREATE VIEW vw_gen_accounts
AS
SELECT
gen_accounts.account_code, 
gen_accounts.account_name, 
gen_accounts.open_balance_dr, 
gen_accounts.open_balance_cr,
(If((Select Sum(debit_amount) from gen_entry_details where gen_entry_details.account_code = gen_accounts.account_code) Is Null,0,(Select Sum(debit_amount) from gen_entry_details where gen_entry_details.account_code = gen_accounts.account_code))) AS balance_dr,
(If((Select Sum(credit_amount) from gen_entry_details where gen_entry_details.account_code = gen_accounts.account_code) Is Null,0,(Select Sum(credit_amount) from gen_entry_details where gen_entry_details.account_code = gen_accounts.account_code))) AS balance_cr,
(If((Select Sum(debit_amount) from gen_entry_details where gen_entry_details.account_code = gen_accounts.account_code) Is Null,0,(Select Sum(debit_amount) from gen_entry_details where gen_entry_details.account_code = gen_accounts.account_code)) + If(open_balance_dr Is Null,0,open_balance_dr)) AS current_balance_dr,
(If((Select Sum(credit_amount) from gen_entry_details where gen_entry_details.account_code = gen_accounts.account_code) Is Null,0,(Select Sum(credit_amount) from gen_entry_details where gen_entry_details.account_code = gen_accounts.account_code)) + If(open_balance_cr Is Null,0,open_balance_cr)) AS current_balance_cr,
(IF((Select Sum(debit_amount) from gen_entry_details where gen_entry_details.account_code = gen_accounts.account_code) > (Select Sum(credit_amount) from gen_entry_details where gen_entry_details.account_code = gen_accounts.account_code) , (Select Sum(debit_amount) from gen_entry_details where gen_entry_details.account_code = gen_accounts.account_code) - (Select Sum(credit_amount) from gen_entry_details where gen_entry_details.account_code = gen_accounts.account_code) , '0')) As final_balance_dr,
(IF((Select Sum(debit_amount) from gen_entry_details where gen_entry_details.account_code = gen_accounts.account_code) < (Select Sum(credit_amount) from gen_entry_details where gen_entry_details.account_code = gen_accounts.account_code) , (Select Sum(credit_amount) from gen_entry_details where gen_entry_details.account_code = gen_accounts.account_code) - (Select Sum(debit_amount) from gen_entry_details where gen_entry_details.account_code = gen_accounts.account_code) , '0')) As final_balance_cr
FROM gen_accounts;

-- (If(debit_balance Is Null,0,debit_balance) + If(open_balance_dr Is Null,0,open_balance_dr)) AS current_balanc_dr,
-- (If(credit_balance Is Null,0,credit_balance) + If(open_balance_cr Is Null,0,open_balance_cr)) AS current_balanc_cr,
