####Command to start vault
**_vault server -config test.hcl_**

####Command to set vault Address
**_export VAULT_ADDR=http://127.0.0.1:8200_**

####Command to init vault
_**vault operator init**_

####Command to unseal vault
_**vault operator unseal**_


####Command to enable KV Secrets Engine
**_vault secrets enable -path=secret/ kv_**

####Command to put secret vault
**_vault kv put secret/vault-config example.username=demouser example.password=demopassword_**

####Command to get secret vault
**_vault kv get secret/vault-config_**

####Test.hcl contents

disable_mlock = true
listener "tcp" {
  address     = "127.0.0.1:8200"
  tls_disable = 1

}

path "secret/foo" {
  capabilities = ["read","write"]
}

path "secret/foo/" {
  capabilities = ["list"]
}

storage "file" {
  path = "/home/shreekar/vault/data"
}


####Export param for vault client
**_export VAULT_ADDR='http://127.0.0.1:8200'_**

**_export VAULT_TOKEN="s.L1TODuJ9l9T0uJhMdnc7znIB"_**



vault secrets enable mysql

vault write mysql/config/connection connection_url="root:admin123@tcp(127.0.0.1:3306)/"
	
	
	vault write mysql/roles/readonly \ db_name=mydb \sql="CREATE USER '{{name}}'@'localhost' IDENTIFIED BY '{{password}}';GRANT SELECT ON *.* TO '{{name}}'@'localhost';
	
	vault write mysql/roles/readonly \sql="CREATE USER '{{name}}'@'%' IDENTIFIED BY '{{password}}';GRANT SELECT ON *.* TO '{{name}}'@'%';"
	
	vault read mysql/creds/readonly
		

GRANT ALL PRIVILEGES ON *.* TO ''@'172.19.0.1' IDENTIFIED BY 'admin' WITH GRANT OPTION;


