from fastapi import FastAPI
from pydantic import BaseModel
import openai
import os
from dotenv import load_dotenv

load_dotenv()

openai.api_key = os.getenv("OPENAI_API_KEY")

class TextRequest(BaseModel):
    text: str

class SummaryResponse(BaseModel):
    summary: str

app = FastAPI()

@app.post("/summarize", response_model=SummaryResponse)
async def summarize(req: TextRequest):
    try:
        response = openai.ChatCompletion.create(
            model="gpt-3.5-turbo",
            messages=[
                {"role": "user", "content": f"Summarize this: {req.text}"}
            ]
        )
        summary = response['choices'][0]['message']['content']
        return {"summary": summary}
    except Exception as e:
        return {"summary": f"Error: {str(e)}"}
