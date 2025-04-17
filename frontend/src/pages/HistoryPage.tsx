import React, { useEffect, useState } from 'react';
import axios from 'axios';

const HistoryPage: React.FC = () => {
  const [history, setHistory] = useState<any[]>([]);

  useEffect(() => {
    axios.get(`${process.env.REACT_APP_BACKEND_API}/api/history`)
      .then(res => setHistory(res.data))
      .catch(() => alert("Error fetching history"));
  }, []);

  return (
    <div>
      <h1>History</h1>
      <ul>
        {history.map((item, idx) => (
          <li key={idx}>
            <strong>{item.url}</strong>: {item.summary}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default HistoryPage;
