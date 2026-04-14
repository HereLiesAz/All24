
import React from 'react';
import { HashRouter as Router, Routes, Route } from 'react-router-dom';
import { Layout } from './components/Layout';
import { Home } from './views/Home';
import { Explore } from './views/Explore';
import { Randomizer } from './views/Randomizer';
import { CrystalBall } from './views/CrystalBall';
import { SettingsAltar } from './views/SettingsAltar';

const App: React.FC = () => {
  return (
    <Router>
      <Layout>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/explore" element={<Explore />} />
          <Route path="/category/:arcana" element={<Explore />} />
          <Route path="/random" element={<Randomizer />} />
          <Route path="/crystal-ball" element={<CrystalBall />} />
          <Route path="/settings" element={<SettingsAltar />} />
        </Routes>
      </Layout>
    </Router>
  );
};

export default App;
