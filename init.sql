CREATE VIEW customer_v
AS
SELECT id,
       name,
       substr(phone, instr(phone, '(') + 1, 3) as code,
       substr(phone, instr(phone, ')') + 2)    as number
FROM customer