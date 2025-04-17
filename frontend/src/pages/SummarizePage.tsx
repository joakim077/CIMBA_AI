import React, { useState } from 'react';
import axios from 'axios';

const SummarizePage: React.FC = () => {
  const [url, setUrl] = useState('');
  const [summary, setSummary] = useState('');

  const handleSubmit = async () => {
    try {
      const res = await axios.post(`${process.env.REACT_APP_BACKEND_API}/api/summarize`, { url });
      setSummary(res.data.summary);
    } catch (err) {
      alert("Error fetching summary");
    }
  };

  return (
    <div>
      <h1>Summarize a Website</h1>
      <input value={url} onChange={e => setUrl(e.target.value)} placeholder="Enter URL" />
      <button onClick={handleSubmit}>Submit</button>
      <pre>{summary}</pre>
    </div>
  );
};

export default SummarizePage;

