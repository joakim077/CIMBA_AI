### Install MINIKUBE
```
minikube start
kubectl create namespace cimba
```

### Node 

#### / /history
```bash
npx create-react-app frontend --template typescript
cd frontend
npm install axios react-router-dom


npm run build
```

### Backend - Java Spring Boot
#### /api/summarize

1. Initialize Spring Boot Project
Use https://start.spring.io/ with:

Language: Java

Build: Gradle

Dependencies: Spring Web, Lombok

Unzip and rename to backend/, or run:

```bash
curl https://start.spring.io/starter.zip -d dependencies=web,lombok -d language=java -d build=gradle -d type=gradle-project -o backend.zip
unzip backend.zip -d backend
```

### Scala Module (Logic + PostgreSQL)

```sql
CREATE TABLE history (
  id SERIAL PRIMARY KEY,
  url TEXT NOT NULL,
  summary TEXT NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

### Python FastAPI - LLM
```
pip install fastapi uvicorn openai
```

```Java
from fastapi import FastAPI, Request
from pydantic import BaseModel
import openai

app = FastAPI()

class Content(BaseModel):
    text: str

@app.post("/summarize")
async def summarize(content: Content):
    response = openai.ChatCompletion.create(
        model="gpt-3.5-turbo",
        messages=[{"role": "user", "content": f"Summarize this: {content.text}"}]
    )
    return {"summary": response["choices"][0]["message"]["content"]}

```


### Database Setup
```
helm repo add bitnami https://charts.bitnami.com/bitnami
helm install postgres bitnami/postgresql --namespace cimba
```

```
kubectl get secret --namespace cimba postgres -o jsonpath="{.data.postgresql-password}" | base64 --decode
```
