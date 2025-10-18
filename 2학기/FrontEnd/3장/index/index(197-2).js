import React from 'react';
import ReactDOM from 'react-dom/client';
import './mysass.scss';

const Header = () => {
  return (
    <>
      <h1>Hello New Style!</h1>
      <p>Add a little style!.</p>
    </>
  );
}

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(<Header />);