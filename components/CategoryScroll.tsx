
import React, { useState } from 'react';
import { NavLink } from 'react-router-dom';
import { Arcana } from '../types';
import { ARCANA_SHORT_DESCRIPTIONS, LOCATIONS } from '../constants';
import { DadaistWrapper } from './DadaistWrapper';

const ARCANA_IMAGE_MAP = LOCATIONS.reduce((acc, loc) => {
    if (!acc[loc.arcana]) {
        acc[loc.arcana] = loc.image;
    }
    return acc;
}, {} as Record<Arcana, string>);

const getCategoryImage = (arcana: Arcana) => {
    return ARCANA_IMAGE_MAP[arcana] || "https://images.unsplash.com/photo-1543501799-a3c306d28dd9?q=80&w=600&auto=format&fit=crop";
}

export const CategoryScroll: React.FC = () => {
    const [activeIndex, setActiveIndex] = useState<number | null>(null);

    const categories = Object.keys(ARCANA_SHORT_DESCRIPTIONS) as Arcana[];

    return (
        <div className="flex flex-col gap-8 py-4 px-2">
            {categories.map((arcana, index) => {
                const isActive = activeIndex === index;
                const shortDesc = ARCANA_SHORT_DESCRIPTIONS[arcana];
                const image = getCategoryImage(arcana);

                return (
                    <DadaistWrapper
                        key={arcana}
                        className={`group relative overflow-hidden rounded-2xl h-[60vh] transition-all duration-[800ms] ease-in-out will-change-transform cursor-pointer border border-white/10 preserve-3d animate-float ${isActive ? 'scale-[1.05] z-50 border-[var(--accent-c)] shadow-[0_20px_50px_rgba(45,212,191,0.5)] -translate-y-4' : 'scale-100 hover:scale-[1.03] hover:border-white/30 hover:shadow-2xl hover:-translate-y-2'}`}
                        onClick={() => setActiveIndex(isActive ? null : index)}
                    >
                        <img
                            src={image}
                            alt={arcana}
                            className={`w-full h-full object-cover block transition-all duration-1000 pixel-image origin-center ${isActive ? 'brightness-110 saturate-150 scale-125 translate-z-10 animate-breathe' : 'brightness-50 grayscale-[0.3] group-hover:scale-110'}`}
                        />

                        {/* Static Overlay for Category Name when not active */}
                        {!isActive && (
                             <div className="absolute inset-0 bg-gradient-to-t from-black/90 via-black/20 to-transparent flex flex-col justify-end p-8 transition-transform duration-500 group-hover:translate-y-[-10px]">
                                <h3 className="text-white text-3xl font-glitch uppercase tracking-[0.2em] glitch-text leading-tight group-hover:scale-110 transform origin-left transition-transform duration-300">{arcana}</h3>
                                <div className="h-0.5 w-0 group-hover:w-32 bg-[var(--accent-p)] transition-all duration-700 ease-out"></div>
                             </div>
                        )}

                        {/* Active Detail Overlay */}
                        <div className={`absolute inset-0 flex items-end p-8 bg-gradient-to-t from-black/95 via-black/50 to-transparent transition-all duration-700 preserve-3d ${isActive ? 'opacity-100 translate-y-0 translate-z-20' : 'opacity-0 translate-y-20 pointer-events-none'}`}>
                            <div className={`flex flex-col gap-3 w-full transition-all duration-1000 delay-100 ${isActive ? 'translate-y-0 opacity-100' : 'translate-y-10 opacity-0'}`}>
                                <h3 className="text-[var(--accent-c)] text-4xl font-glitch uppercase tracking-[0.1em] drop-shadow-[0_0_20px_rgba(45,212,191,0.8)] leading-tight animate-pulse">{arcana}</h3>
                                <div className="h-px w-24 bg-[var(--accent-c)] mb-2 group-hover:w-full transition-all duration-1000"></div>
                                <p className="text-white font-mono text-sm mb-8 leading-relaxed italic border-l-2 border-[var(--accent-p)] pl-4 max-w-[80%] hover:pl-6 transition-all duration-300">
                                    {shortDesc}
                                </p>
                                <NavLink
                                    to={`/category/${encodeURIComponent(arcana)}`}
                                    onClick={(e) => e.stopPropagation()}
                                    className="inline-block w-fit px-8 py-4 bg-black/80 backdrop-blur-md border border-[var(--accent-c)] text-[var(--accent-c)] font-mono text-xs uppercase tracking-[0.3em] hover:bg-[var(--accent-c)] hover:text-black transition-all duration-300 font-bold shadow-[6px_6px_0px_var(--accent-p)] hover:shadow-[12px_12px_0px_var(--accent-p)] hover:-translate-y-1 hover:-translate-x-1 active:translate-x-[2px] active:translate-y-[2px] active:shadow-none animate-pulse-glow"
                                >
                                    OPEN THE DECK
                                </NavLink>
                            </div>
                        </div>
                    </DadaistWrapper>
                );
            })}
        </div>
    );
};
