
import React, { useState } from 'react';
import { LOCATIONS, NHEN_PICK_NAMES } from '../constants';
import { LocationCard } from '../components/LocationCard';
import { NavLink } from 'react-router-dom';
import { ArrowLeft } from 'lucide-react';
import { NolaLocation } from '../types';

const NHEN_NAMES_SET = new Set(NHEN_PICK_NAMES);
const CANDIDATE_LOCATIONS = LOCATIONS.filter(loc =>
  NHEN_NAMES_SET.has(loc.name)
);

export const CrystalBall: React.FC = () => {
  const [prediction, setPrediction] = useState<NolaLocation | null>(null);
  const [isGazing, setIsGazing] = useState(false);

  const gazeIntoCrystal = () => {
    if (isGazing) return;
    setIsGazing(true);
    setPrediction(null);

    // Simulate gazing time
    setTimeout(() => {
      if (CANDIDATE_LOCATIONS.length > 0) {
        const randomPick = CANDIDATE_LOCATIONS[Math.floor(Math.random() * CANDIDATE_LOCATIONS.length)];
        setPrediction(randomPick);
      }

      setIsGazing(false);
    }, 2500);
  };

  return (
    <div className="py-8 min-h-[80vh] flex flex-col items-center justify-center space-y-12 animate-in fade-in duration-700">

      {/* Back Button */}
      <div className="w-full flex justify-start">
         <NavLink to="/" className="text-[var(--accent-c)] flex items-center gap-2 hover:text-white transition-colors">
            <ArrowLeft size={20} className="pixel-icon" /> <span className="font-mono text-xs uppercase tracking-widest">Return to Deck</span>
         </NavLink>
      </div>

      <div className="text-center space-y-2">
        <h2 className="font-glitch text-4xl text-[var(--accent-p)] glitch-text uppercase">THE ORACLE</h2>
        <p className="text-[var(--accent-c)] text-[11px] font-mono uppercase tracking-[0.2em] font-bold italic">One destiny, chosen by the void.</p>
      </div>

      <div className="relative cursor-pointer group" onClick={gazeIntoCrystal}>
        {/* Crystal Ball Visual */}
        <div className={`w-64 h-64 rounded-full bg-gradient-to-br from-[var(--bg-teal)] to-black border-4 border-[var(--accent-c)]/30 relative overflow-hidden shadow-[0_0_50px_var(--accent-c)] transition-all duration-1000 ${isGazing ? 'animate-pulse scale-105 shadow-[0_0_80px_var(--accent-p)] border-[var(--accent-p)]' : 'hover:scale-105'}`}>

            {/* Inner fog/smoke animation */}
            <div className="absolute inset-0 bg-[url('https://media1.giphy.com/media/v1.Y2lkPTc5MGI3NjExZmFxdm55bmh6aW42amYxbmh6aW42amYxbmh6aW42amYxbmh6aW42bSZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9cw/l4FGpP4lZE6ZHuApO/giphy.gif')] bg-cover opacity-30 mix-blend-overlay"></div>

            {/* Glitch Overlay */}
            <div className="absolute inset-0 bg-gradient-to-t from-black via-transparent to-transparent opacity-50"></div>

            <div className="absolute inset-0 flex items-center justify-center">
                 {!isGazing && !prediction && (
                    <span className="font-glitch text-6xl text-white/20 group-hover:text-white/80 transition-colors">?</span>
                 )}
            </div>
        </div>
      </div>

      <div className="min-h-[300px] w-full flex justify-center">
        {isGazing ? (
           <p className="font-mono text-[12px] text-[var(--accent-c)] animate-pulse uppercase tracking-[0.4em] font-bold mt-8">SUMMONING LOCATION...</p>
        ) : prediction ? (
           <div className="animate-in zoom-in slide-in-from-bottom-10 duration-700">
              <div className="text-center mb-6">
                <span className="text-[12px] font-mono text-white/50 uppercase tracking-widest border-x border-white/20 px-4">Your Destination</span>
              </div>
              <LocationCard location={prediction} />
           </div>
        ) : (
           <button
             onClick={gazeIntoCrystal}
             className="mt-8 px-8 py-3 bg-[var(--card-bg)] border border-[var(--accent-p)] text-white font-glitch tracking-widest hover:bg-[var(--accent-p)] transition-all uppercase"
           >
             GAZE INTO THE BALL
           </button>
        )}
      </div>

    </div>
  );
};
