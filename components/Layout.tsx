
import React, { ReactNode, useState, useEffect } from 'react';
import { NavLink } from 'react-router-dom';
import { Compass, Settings, Sparkles } from 'lucide-react';
import { NorthStar } from './NorthStar';
import { IsolatedStarIcon, TarotCardIcon } from './CustomIcons';
import { SplashScreen } from './SplashScreen';

interface LayoutProps { children: ReactNode; }

export const Layout: React.FC<LayoutProps> = ({ children }) => {
  const [showSplash, setShowSplash] = useState(true);

  useEffect(() => {
    const hasSeen = sessionStorage.getItem('seen_splash');
    if (hasSeen) setShowSplash(false);
  }, []);

  const finishSplash = () => {
    setShowSplash(false);
    sessionStorage.setItem('seen_splash', 'true');
  };

  if (showSplash) return <SplashScreen onComplete={finishSplash} />;

  return (
    <div className="min-h-screen relative flex flex-col items-center">
      {/* Background is handled by body to ensure coverage */}

      {/* Header */}
      <header className="w-full p-6 flex justify-between items-center z-10">
        <h1 className="font-glitch text-4xl tracking-widest text-[var(--accent-c)] glitch-text uppercase">NEON OCCULT</h1>
        <div className="flex items-center gap-3">
          <NavLink to="/settings" className="p-2 text-[var(--accent-p)] hover:text-white transition-colors">
            <Settings size={22} className="pixel-icon" strokeWidth={2} />
          </NavLink>
        </div>
      </header>

      <main className="w-full max-w-md px-4 flex-grow z-10 pb-40 landscape:max-w-4xl">
        {children}
      </main>

      <NorthStar />

      {/* Navigation & Footer */}
      <nav className="fixed bottom-0 left-0 w-full bg-black/95 backdrop-blur-xl border-t border-[var(--accent-c)]/30 px-6 py-4 z-50">

        <div className="flex w-full justify-around items-center max-w-lg mx-auto">
            <NavLink to="/" className={({ isActive }) => `flex flex-col items-center gap-2 transition-all ${isActive ? 'text-[var(--accent-c)] scale-110 drop-shadow-[0_0_5px_var(--accent-c)]' : 'text-white/60 hover:text-white'}`}>
            <IsolatedStarIcon size={24} className="pixel-icon" />
            <span className="text-[10px] font-mono uppercase tracking-widest font-bold">Deck</span>
            </NavLink>
            <NavLink to="/explore" className={({ isActive }) => `flex flex-col items-center gap-2 transition-all ${isActive ? 'text-[var(--accent-c)] scale-110 drop-shadow-[0_0_5px_var(--accent-c)]' : 'text-white/60 hover:text-white'}`}>
            <Compass size={24} className="pixel-icon" strokeWidth={2} />
            <span className="text-[10px] font-mono uppercase tracking-widest font-bold">Arcana</span>
            </NavLink>
            <NavLink to="/crystal-ball" className={({ isActive }) => `flex flex-col items-center gap-2 transition-all ${isActive ? 'text-[var(--accent-c)] scale-110 drop-shadow-[0_0_5px_var(--accent-c)]' : 'text-white/60 hover:text-white'}`}>
            <Sparkles size={24} className="pixel-icon" strokeWidth={2} />
            <span className="text-[10px] font-mono uppercase tracking-widest font-bold">Oracle</span>
            </NavLink>
            <NavLink to="/random" className={({ isActive }) => `flex flex-col items-center gap-2 transition-all ${isActive ? 'text-[var(--accent-c)] scale-110 drop-shadow-[0_0_5px_var(--accent-c)]' : 'text-white/60 hover:text-white'}`}>
            <TarotCardIcon size={24} className="pixel-icon" />
            <span className="text-[10px] font-mono uppercase tracking-widest font-bold">Deal</span>
            </NavLink>
        </div>

        {/* Glitch Footer Link */}
        <div className="absolute -top-12 right-0 left-0 flex flex-col items-center justify-center pointer-events-none">
           <span className="text-[10px] font-mono text-[var(--accent-p)] opacity-70 mb-1 pointer-events-auto">•´¯`•» ηℍ𝐞𝓷 𝔫Ⓗᵉ几 ή𝐇𝐞η «•´¯`•</span>
           <a href="https://instagram.com/_nh_en" target="_blank" rel="noopener noreferrer" className="text-[12px] font-mono text-[var(--accent-c)] hover:text-white transition-colors pointer-events-auto">
              @_nh_en
           </a>
        </div>

      </nav>
    </div>
  );
};
