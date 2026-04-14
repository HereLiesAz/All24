import React, { useState, useEffect } from 'react';
import { PixelAlligatorIcon } from './CustomIcons';

export const SplashScreen: React.FC<{ onComplete: () => void }> = ({ onComplete }) => {
  const [isDissolving, setIsDissolving] = useState(false);

  useEffect(() => {
    const timer = setTimeout(() => {
      setIsDissolving(true);
      setTimeout(() => onComplete(), 1200);
    }, 2000);
    return () => clearTimeout(timer);
  }, [onComplete]);

  return (
      <div className="fixed inset-0 z-[100] bg-[var(--charcoal)] flex items-center justify-center overflow-hidden">
        <div className={`relative w-full h-full flex flex-col items-center justify-center ${isDissolving ? 'animate-dissolve' : ''}`}>
           <div className="relative mb-8">
             <div className="absolute inset-0 bg-[var(--accent-c)] blur-[40px] opacity-20"></div>
             <PixelAlligatorIcon size={120} className="text-[var(--accent-c)] glitch-text relative z-10" />
           </div>
           <h1 className="font-glitch text-6xl text-white glitch-text tracking-widest z-10">NEON OCCULT</h1>
           <span className="font-mono text-[10px] tracking-[0.5em] text-[var(--accent-p)] uppercase mt-4 z-10">Guide to the Glitch</span>
        </div>
      </div>
  );
};
