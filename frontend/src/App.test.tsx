import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';

it('renders without crashing', () => {
  const root = ReactDOM.createRoot(document.getElementById('root')!);
  root.render(<App />);
});
