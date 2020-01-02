####Command to start vault
**_vault server -config test.hcl_**

####Command to init vault
_**vault operator init**_

####Command to unseal vault
_**vault operator unseal**_

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

https://dzone.com/articles/managing-your-database-secrets-with-vault

