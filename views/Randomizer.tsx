
import React, { useState, useEffect } from 'react';
import { LOCATIONS } from '../constants';
import { NolaLocation } from '../types';
import { LocationCard } from '../components/LocationCard';
import { TarotCardIcon } from '../components/CustomIcons';

export const Randomizer: React.FC = () => {
  const [hand, setHand] = useState<NolaLocation[]>([]);
  const [isDealing, setIsDealing] = useState(false);

  const dealCards = () => {
    setIsDealing(true);
    setTimeout(() => {
      const shuffled = [...LOCATIONS].sort(() => 0.5 - Math.random());
      setHand(shuffled.slice(0, 3));
      setIsDealing(false);
    }, 1200);
  };

  useEffect(() => {
    dealCards();
  }, []);

  return (
    <div className="py-8 space-y-12">
      <div className="text-center space-y-2">
        <h2 className="font-glitch text-5xl text-[var(--accent-p)] glitch-text uppercase">THE SPREAD</h2>
        <p className="text-[var(--accent-c)] text-[11px] font-mono uppercase tracking-[0.2em] font-bold italic">Three cards reveal your spectral coordinates</p>
      </div>

      <div className="space-y-32">
        {isDealing ? (
          <div className="flex flex-col items-center justify-center h-80 space-y-6">
            <div className="relative">
              <TarotCardIcon className="text-[var(--accent-p)] animate-bounce pixel-icon" size={100} />
              <div className="absolute inset-0 animate-pulse bg-[var(--accent-p)]/20 blur-2xl"></div>
            </div>
            <p className="font-mono text-[12px] text-[var(--accent-c)] animate-pulse uppercase tracking-[0.4em] font-bold">CONSULTING THE VOID...</p>
          </div>
        ) : (
          hand.map((loc, idx) => (
            <div key={loc.id} className="animate-in fade-in zoom-in slide-in-from-bottom-10 duration-700" style={{ animationDelay: `${idx * 300}ms` }}>
              <div className="text-center mb-6">
                <span className="text-[12px] font-mono text-white/50 uppercase tracking-widest border-x border-white/20 px-4">Position {idx + 1}</span>
              </div>
              <LocationCard location={loc} />
            </div>
          ))
        )}
      </div>

      <div className="pt-20 flex justify-center">
        <button
          onClick={dealCards}
          disabled={isDealing}
          className="bg-[var(--card-bg)] border border-[var(--accent-c)] px-10 py-5 flex items-center gap-4 hover:bg-[var(--accent-c)] hover:text-black transition-all group shadow-[0_0_30px_rgba(45,212,191,0.2)]"
        >
          <TarotCardIcon className={`text-[var(--accent-c)] group-hover:text-black pixel-icon ${isDealing ? 'animate-pulse' : ''}`} size={32} />
          <span className="font-glitch text-2xl tracking-widest uppercase">SHUFFLE FATE</span>
        </button>
      </div>
    </div>
  );
};
